<template>
  <v-container
    id="regular-tables"
    fluid
    tag="section"
  >
    <v-btn
      style="margin-left: 20px"
      outlined
      small
      color="green"
      @click="initData"
    >
      <v-icon>mdi-cached</v-icon>
      refresh
    </v-btn>
    <base-material-card
      color="success"
      dark
      icon="mdi-paw"
      title="Messages"
      class="px-5 py-3"
    >
      <v-simple-table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Subject</th>
            <th>Detail</th>
            <th>
              Create Time
            </th>
          </tr>
        </thead>

        <tbody
          v-for="(item,index) in msg"
          :key="index"
        >
          <tr>
            <td>{{ item.name }}</td>
            <td>{{ item.email }}</td>
            <td>{{ item.subject }}</td>
            <td>
              <v-row justify="center">
                <v-dialog
                  width="600px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      color="primary"
                      icon
                      dark
                      v-bind="attrs"
                      v-on="on"
                    >
                      <v-icon>
                        mdi-shape-square-rounded-plus
                      </v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-text>
                      {{ item.msg }}
                    </v-card-text>
                  </v-card>
                </v-dialog>
              </v-row>
            </td>
            <td>
              {{ item.createTime }}
            </td>
            <td>
              <v-btn
                color="primary"
                icon
                dark
                @click="deleteMsg(item.id)"
              >
                <v-icon>
                  mdi-delete
                </v-icon>
              </v-btn>
            </td>
          </tr>
        </tbody>
      </v-simple-table>
    </base-material-card>
  </v-container>
</template>

<script>
  export default {
    name: 'DashboardNotifications',
    data: () => ({
      msg: [],
    }),
    created () {
      this.initData()
    },
    methods: {
      initData () {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/allMsg?token=' + token).then(res => {
          // console.log(res.data.data)
          _this.msg = res.data.data
        }).catch(e => {
          alert(e)
        })
      },
      deleteMsg (id) {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/deleteMsg?token=' + token + '&id=' + id).then(res => {
          // console.log(res)
          _this.initData() // 更新数据
        }).catch(e => {
          alert(e)
        })
      },
    },
  }
</script>
