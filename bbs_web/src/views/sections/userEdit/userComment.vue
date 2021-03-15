<template>
  <v-list>
    <template v-for="(item, index) in myCommentList">
      <v-list-item :key="index+'a'">
        <v-list-item-content>
          <v-list-item-subtitle v-text="'#'+(index+1)" />
          <v-container>
            <h4>{{ item.content }}</h4>
          </v-container>
          <h6>{{ item.createTime }}</h6>
        </v-list-item-content>
        <!-------------------------------------------------->
        <v-dialog
          transition="dialog-bottom-transition"
          max-width="600"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              elevation="2"
              v-bind="attrs"
              x-small
              color="secondary"
              v-on="on"
              @click="showPost(item.subjectId)"
            >
              Origin
            </v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card>
              <v-toolbar
                color="accent"
                dark
              >
                {{ post.title }}
              </v-toolbar>
              <v-card-text style="margin-top: 10px">
                <div>
                  <h4>{{ post.briefing }}</h4>
                  <div v-html="post.detail" />
                </div>
                <h6>{{ post.createTime }}</h6>
              </v-card-text>
              <v-card-actions class="justify-end">
                <v-btn
                  text
                  @click="dialog.value = false"
                >
                  Close
                </v-btn>
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>
        <!------------------------------------------------------>
        <div style="width: 5px" />
        <v-tooltip top>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              elevation="2"
              icon
              x-small
              v-bind="attrs"
              v-on="on"
              @click="deleteComment(item.id)"
            >
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </template>
          <span>Delete</span>
        </v-tooltip>
      </v-list-item>

      <v-divider
        :key="index"
      />
    </template>
  </v-list>
</template>

<script>
  export default {
    name: 'UserCommentVue',
    data () {
      return {
        myCommentList: [],
        post: {},
      }
    },
    created () {
      this.initData()
    },
    methods: {
      deleteComment (index) {
        const _this = this
        this.$axios.get('/user/delete/comment?commentID=' + index, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          _this.$toast(res.data.msg)
          _this.initData()
        })
      },
      showPost (index) {
        const _this = this
        this.$axios.get('/post/query?postID=' + index).then(res => {
          _this.post = res.data.data
        }).catch(err => {
          _this.post = {}
          _this.$toast(err)
        })
      },
      initData () {
        const _this = this
        this.$axios.get('/user/comment/get', {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          console.log(res.data.data)
          _this.myCommentList = res.data.data
        })
      },
    },
  }
</script>

<style scoped>

</style>
