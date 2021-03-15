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
      icon="mdi-snail"
      title="Comments"
      class="px-5 py-3"
    >
      <v-simple-table>
        <thead>
          <tr>
            <th class="primary--text">
              ID
            </th>
            <th class="primary--text">
              Content
            </th>
            <th class="primary--text">
              Create Time
            </th>
            <th class="primary--text">
              Publish User
            </th>
            <th class="text-right primary--text">
              Post ID
            </th>
          </tr>
        </thead>

        <tbody
          v-for="(item,index) in comment"
          :key="index"
        >
          <tr>
            <td>{{ item.id }}</td>
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
                      <div>{{ item.content }}</div>
                    </v-card-text>
                  </v-card>
                </v-dialog>
              </v-row>
            </td>
            <td>{{ item.createTime }}</td>
            <td>{{ item.publishUser }}</td>
            <td class="text-right">
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
                      @click="getPost(item.subjectId)"
                    >
                      view post
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span>{{ post.title }}</span>
                    </v-card-title>
                    <v-card-text>
                      <div>{{ post.briefing }}</div>
                      <v-divider />
                      <div>{{ post.detail }}</div>
                    </v-card-text>
                  </v-card>
                </v-dialog>
              </v-row>
            </td>
            <v-btn
              color="primary"
              icon
              dark
              @click="deleteComment(item.id)"
            >
              <v-icon>
                mdi-delete
              </v-icon>
            </v-btn>
          </tr>
        </tbody>
      </v-simple-table>
    </base-material-card>
  </v-container>
</template>

<script>

  export default {
    data: () => ({
      comment: [],
      post: {},
    }),
    created () {
      this.initData()
      this.post = {}
    },
    methods: {
      initData () {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/management/comments?token=' + token).then(res => {
          // console.log(res.data.data)
          _this.comment = res.data.data
        }).catch(e => {
          alert(e)
        })
      },
      deleteComment (id) {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/management/deleteComment?token=' + token + '&id=' + id).then(res => {
          // console.log(res)
          _this.initData() // 更新数据
        }).catch(e => {
          alert(e)
        })
      },
      getPost (id) {
        const _this = this
        const token = 'hjhuvyjki8979@xwe8923hwd823AASW'
        this.$axios.post('/management/getPost?token=' + token + '&id=' + id).then(res => {
          _this.post = res.data.data
        }).catch(e => {
          alert(e)
        })
      },
    },
  }
</script>
