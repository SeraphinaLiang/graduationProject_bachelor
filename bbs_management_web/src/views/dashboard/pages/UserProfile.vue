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
      icon="mdi-unicorn-variant"
      title="User Information"
      class="px-5 py-3"
    >
      <v-simple-table>
        <thead>
          <tr>
            <th class="primary--text">
              ID
            </th>
            <th class="primary--text">
              Full Name
            </th>
            <th class="primary--text">
              Username
            </th>
            <th class="primary--text">
              Email
            </th>
            <th class="primary--text">
              Gender
            </th>
            <th class="text-right primary--text">
              Register Time
            </th>
          </tr>
        </thead>

        <tbody
          v-for="(item,index) in user"
          :key="index"
        >
          <tr>
            <td>{{ item.id }}</td>
            <td>{{ item.fullname }}</td>
            <td>{{ item.username }}</td>
            <td>{{ item.email }}</td>
            <td>{{ gender[item.gender] }}</td>
            <td class="text-right">
              {{ item.createTime }}
            </td>
            <td>
              <v-btn
                color="primary"
                icon
                dark
                @click="deleteUser(item.id)"
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

    <div class="py-3" />
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      user: [],
      gender: [
        'Female',
        'Male',
        'NaN',
        'NaN',
      ],
    }),
    created () {
      this.initData()
    },
    methods: {
      initData () {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/management/users?token=' + token).then(res => {
          // console.log(res.data.data)
          _this.user = res.data.data
        }).catch(e => {
          alert(e)
        })
      },
      deleteUser (id) {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/management/deleteUser?token=' + token + '&id=' + id).then(res => {
          // console.log(res)
          _this.initData() // 更新数据
        }).catch(e => {
          alert(e)
        })
      },
    },
  }
</script>
