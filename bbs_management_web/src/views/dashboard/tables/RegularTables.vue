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
      icon="mdi-leaf-maple"
      title="Posts Overview"
      class="px-5 py-3"
    >
      <v-simple-table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Module</th>
            <th>Create Time</th>
            <th>Update Time</th>
            <th class="text-right">
              Publish User
            </th>
          </tr>
        </thead>

        <tbody
          v-for="(item,index) in post"
          :key="index"
        >
          <tr>
            <td>{{ item.id }}</td>
            <td>{{ item.title }}</td>
            <td>{{ module [item.module-1] }}</td>
            <td>{{ item.createTime }}</td>
            <td>{{ item.updateTime }}</td>
            <td class="text-right">
              {{ item.publishUser }}
            </td>
            <td>
              <v-btn
                color="primary"
                icon
                dark
                @click="deletePost(item.id)"
              >
                <v-icon>
                  mdi-delete
                </v-icon>
              </v-btn>
            </td>
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
                    <v-card-title>
                      <span>{{ item.title }}</span>
                    </v-card-title>
                    <v-card-text>
                      <div>{{ item.briefing }}</div>
                      <v-divider />
                      <div v-html="item.detail" />
                    </v-card-text>
                  </v-card>
                </v-dialog>
              </v-row>
            </td>
          </tr>
        </tbody>
      </v-simple-table>
    </base-material-card>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      post: [],
      module: [
        '欧洲',
        '北美',
        '亚洲',
        '澳洲',
        '香港',
        '英国',
        '留学申请',
        '签证移民',
        '海外生活',
        '本地求职',
        '学习研究',
        '好物分享',
      ],
    }),
    created () {
      this.initData()
    },
    methods: {
      initData () {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/management/posts?token=' + token).then(res => {
          // console.log(res.data.data)
          _this.post = res.data.data
        }).catch(e => {
          alert(e)
        })
      },
      deletePost (id) {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/management/deletePost?token=' + token + '&id=' + id).then(res => {
          // console.log(res)
          _this.initData() // 更新数据
        }).catch(e => {
          alert(e)
        })
      },
    },
  }
</script>
