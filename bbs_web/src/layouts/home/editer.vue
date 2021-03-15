<template>
  <div>
    <form :model="postContent">
      <!---------------------title-------------------------->
      <v-col
        cols="12"
        sm="6"
      >
        <v-text-field
          v-model="postContent.title"
          :counter="30"
          label="Title 标题"
          outlined
          clearable
        />
      </v-col>
      <!--------------------briefing--------------------------->
      <v-col
        cols="12"
        sm="12"
      >
        <v-text-field
          v-model="postContent.briefing"
          label="Briefing 概述"
          :counter="100"
          outlined
          clearable
        />
      </v-col>
      <!---------------------editer-detail---------------------->
      <div>
        <quill-editor
          ref="myTextEditor"
          v-model="postContent.detail"
          :options="editorOption"
          style="height:400px;top: 20px;"
          @change="onEditorChange($event)"
        />
      </div>
      <div style="min-height: 40px" />
      <div style="margin-top: 40px;margin-left:10px">
        <!---------------------模块选择--------------------->
        <v-container
          fluid
          style="background-color: lightgray;top:20px"
        >
          <v-row align="center">
            <v-col cols="6">
              <v-subheader>
                Which module you want to include your post ?
              </v-subheader>
            </v-col>

            <v-col cols="6">
              <v-select
                v-model="postContent.select"
                :items="items"
                label="Select"
                persistent-hint
                return-object
                single-line
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <h4>插入中文标签，请直接复制粘贴，然后按回车</h4>
              <v-combobox
                v-model="postContent.chosenTags"
                style="margin-top: 10px"
                :items="allTags"
                chips
                clearable
                label="Add some tags (press enter)"
                multiple
                solo
              >
                <template v-slot:selection="{ attrs, item, select, selected }">
                  <v-chip
                    v-bind="attrs"
                    :input-value="selected"
                    close
                    @click="select"
                    @click:close="remove(item)"
                  >
                    <strong>{{ item }}</strong>&nbsp;
                  </v-chip>
                </template>
              </v-combobox>
            </v-col>
          </v-row>
        </v-container>
        <!----------------------2-button--------------------------->
        <v-btn
          style="margin-top: 30px ;margin-right: 5px;background-color: #00695c;width: 20px"
          color="white"
          class="text-center"
          text
          @click="ok"
        >
          PUSH
        </v-btn>

        <v-btn
          style="margin-top: 30px ;width: 20px"
          class="text-center"
          color="secondary"
          text
          @click="cancel"
        >
          Cancel
        </v-btn>
      </div>
    </form>
  </div>
</template>
<script>
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'

  import { quillEditor } from 'vue-quill-editor'

  export default {
    name: 'Editer',
    components: {
      quillEditor,
    },
    data () {
      return {
        postContent: {
          title: '',
          briefing: '',
          detail: '',
          select: [],
          chosenTags: [],
        },
        editorOption: {
          placeholder: 'write something...',
        },
        items: ['留学申请 Application',
                '签证移民 VISA & Immigration',
                '海外生活 Local Experience',
                '本地求职 Job & Career',
                '学习研究 Study & Research',
                '好物分享 Market & Deals',
        ],
        allTags: ['TOEFL', 'GMAT', 'Finance', '2021', 'Computer Science', 'Ielts', 'GRE'],
      }
    },
    computed: {
    },
    methods: {
      onEditorChange ({ editor, html, text }) {
        this.postContent.detail = html
      },
      remove (item) {
        this.postContent.chosenTags.splice(this.postContent.chosenTags.indexOf(item), 1)
        this.postContent.chosenTags = [...this.postContent.chosenTags]
      },
      // 发表帖子
      ok () {
        const _this = this
        if (this.postContent.title !== '') {
          if (this.postContent.briefing !== '') {
            if (this.postContent.detail !== '') {
              if ((this.postContent.chosenTags.length !== 0) && (this.postContent.select.length !== 0)) {
                this.$axios.post('/post/submit', JSON.stringify(this.postContent), {
                  headers: {
                    Authorization: localStorage.getItem('token'),
                  },
                }).then(res => {
                  _this.$toast(res.data.msg)
                }).catch(error => {
                  _this.$toast(error)
                })
              } else {
                this.$toast('模块和标签不能为空')
              }
            } else {
              this.$toast('内容不能为空')
            }
          } else {
            this.$toast('概述不能为空')
          }
        } else {
          this.$toast('标题不能为空')
        }
      },
      cancel () {
        this.postContent.title = ''
        this.postContent.briefing = ''
        this.postContent.detail = ''
        this.$router.push('/')
      },
    },
  }
</script>

<style scoped>

</style>
