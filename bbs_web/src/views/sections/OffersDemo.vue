<template>
  <v-app id="inspire">
    <div
      style="background-color: white"
      @click="srcoll"
    >
      <country />
      <div id="nav" />
    </div>
    <!-----------------------------post demo refresh---------------------------------------------->
    <v-bottom-navigation
      color="white"
      background-color="primary"
      grow
    >
      <v-btn @click="refresh()">
        <span>Refresh</span>
        <v-icon>mdi-autorenew</v-icon>
      </v-btn>
      <v-btn @click="next()">
        <span>Next</span>
        <v-icon>mdi-arrow-right-bold</v-icon>
      </v-btn>
      <v-btn @click="back()">
        <span>Back</span>
        <v-icon>mdi-arrow-left-thick</v-icon>
      </v-btn>
    </v-bottom-navigation>
    <!-----------------------------post demo---------------------------------------------->
    <v-container
      class="py-8 px-6"
      fluid
    >
      <v-row>
        <v-col cols="12">
          <v-card>
            <v-subheader><h3>{{ topic }}</h3></v-subheader>

            <v-list two-line>
              <!----------------------offer-------------------------->
              <v-expansion-panels
                v-model="showingPanel"
                multiple
              >
                <template v-for="(item,index) in offers">
                  <v-expansion-panel
                    :key="index"
                    @click="getOfferDetail(index,item.id)"
                  >
                    <!------------------------缩略------------------------------->
                    <v-expansion-panel-header>
                      <v-list-item>
                        <v-list-item-avatar>
                          <img
                            :src="'data:image/png;base64,'+photo[index]"
                            alt="loading..."
                          >
                        </v-list-item-avatar>
                        <v-list-item-content>
                          <v-list-item-title>{{ item.title }}</v-list-item-title>
                          <v-list-item-subtitle>
                            {{ item.briefing }}
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                      <template v-slot:actions>
                        <v-icon
                          color="primary"
                        >
                          $expand
                        </v-icon>
                      </template>
                    </v-expansion-panel-header>
                    <!-----------------------------展开内容------------------------------->
                    <v-expansion-panel-content>
                      <v-container>
                        <v-row>
                          <v-container style="margin-bottom: 10px">
                            {{ item.detail }}
                          </v-container>
                          <v-row>
                            <v-chip
                              v-for="(tagField,tagIndex) in tags[index]"
                              :key="tagIndex"
                              class="ma-2"
                              color="accent"
                              text-color="accent"
                              outlined
                              style="margin: 0"
                            >
                              <h4>{{ tagField }}</h4>
                            </v-chip>
                          </v-row>
                        </v-row>
                        <v-row>
                          <!----------------------ADD COMMENT------------------------>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                              <v-btn
                                elevation="2"
                                x-small
                                fab
                                color="blue"
                                style="margin-left: 85%"
                                v-bind="attrs"
                                v-on="on"
                                @click="addAComment(index)"
                              >
                                <v-icon color="white">
                                  mdi-pencil
                                </v-icon>
                              </v-btn>
                            </template>
                            <span>Add a comment !</span>
                          </v-tooltip>
                          <!----------------------CHECK ALL COMMENT------------------------>
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                              <v-btn
                                elevation="2"
                                x-small
                                fab
                                color="green"
                                style="margin-left: 10px"
                                v-bind="attrs"
                                @click="showAllTheComments(index)"
                                v-on="on"
                              >
                                <v-icon color="white">
                                  mdi-format-list-bulleted-square
                                </v-icon>
                              </v-btn>
                            </template>
                            <span>Check All Comments !</span>
                          </v-tooltip>
                          <!----------------------STAR POST----------------------->
                          <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                              <v-btn
                                elevation="2"
                                x-small
                                fab
                                v-bind="attrs"
                                style="margin-left: 10px"
                                color="grey"
                                @click="starPost(index,item.id)"
                                v-on="on"
                              >
                                <v-icon
                                  v-if="!stared[index]"
                                  color="white lighten-1"
                                >
                                  mdi-star
                                </v-icon>

                                <v-icon
                                  v-else
                                  color="yellow darken-3"
                                >
                                  mdi-star
                                </v-icon>
                              </v-btn>
                            </template>
                            <span>Star this post ! </span>
                          </v-tooltip>
                          <!------------------------------------------------------>
                        </v-row>
                        <!--------------------current---comment-expend------------------>
                        <div v-show="offerListConfig[index].addComment">
                          <v-container fluid>
                            <div style="height: 10px" />
                            <v-row>
                              <v-textarea
                                v-model="offerListConfig[index].currentComment"
                                label="Comments...."
                                auto-grow
                                outlined
                                rows="3"
                                row-height="25"
                              />
                            </v-row>
                            <v-row>
                              <v-btn
                                color="primary"
                                elevation="2"
                                small
                                style="margin-right: 10px"
                                @click="pushComment(index,item.id,offerListConfig[index].currentComment)"
                              >
                                PUSH
                              </v-btn>
                              <v-btn
                                color="secondary"
                                elevation="2"
                                small
                                @click="cancelComment(index)"
                              >
                                Cancel
                              </v-btn>
                            </v-row>
                          </v-container>
                        </div>
                        <!-------------------------ALL COMMENTS EXPEND------------------------------->
                        <v-container v-show="offerListConfig[index].showAllComments">
                          <h3 style="margin-top: 5px;margin-bottom: 5px;color: #00695c">
                            All Comments
                          </h3>
                          <v-card
                            class="mx-auto"
                          >
                            <v-list>
                              <template v-for="(field, number) in currentOfferComments[index]">
                                <v-list-item :key="number+'a'">
                                  <v-list-item-content>
                                    <v-list-item-subtitle v-text="'#'+(number+1)" />
                                    <v-container
                                      style="margin: 10px"
                                    >
                                      <v-row style="margin-bottom: 10px">
                                        <h4 style="color: #474747">
                                          {{ field.content }}
                                        </h4>
                                      </v-row>
                                      <v-row>
                                        <h5 style="color: #698b75">
                                          {{ field.createTime }}
                                        </h5>
                                      </v-row>
                                    </v-container>
                                  </v-list-item-content>
                                </v-list-item>

                                <v-divider
                                  :key="number"
                                />
                              </template>
                            </v-list>
                          </v-card>
                        </v-container>
                        <!--------------------------------------------------------------------------->
                      </v-container>
                    </v-expansion-panel-content>
                  </v-expansion-panel>
                </template>
              </v-expansion-panels>
              <!----------------------offer-------------------------->
            </v-list>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <!---------------分页------------------>
    <v-container>
      <div class="text-center">
        <v-pagination
          v-model="pageNum"
          :length="total"
          :total-visible="7"
        />
      </div>
    </v-container>
  </v-app>
</template>

<script>
  import country from '@/views/sections/country'
  export default {
    name: 'SectionOffersDemo',
    components: {
      country,
    },
    data: () => ({
      offerListConfig: [
        {
          addComment: false,
          showAllComments: false,
          currentComment: '1',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '2',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '3',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '4',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '5',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '6',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '7',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '8',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '9',
        },
        {
          addComment: false,
          showAllComments: false,
          currentComment: '10',
        },
      ],
      pageNum: 1, // 当前的页数
      showingPanel: [], // 展开了的post
      colors: [
        '#37474f',
        '#37474f',
        '#37474f',
        '#37474f',
        '#37474f',
      ],
      cycle: false,
      addCommentDTO: {
        subjectId: '',
        content: '',
      },
      photo: [],
      moduleID: '',
    }),
    computed: {
      topic: {
        get () {
          return this.$store.state.currentTopicOfOffer
        },
        set (s) {
          this.$store.commit('changeCurrentTopicOfOffer', s)
        },
      },
      offers: { // 界面的帖子
        get () {
          return this.$store.state.offerList
        },
        set (s) {
          this.$store.commit('setOfferList', s)
        },
      },
      currentOfferComments: { // 页面帖子的评论
        get () {
          return this.$store.state.offerComment
        },
        set (s) {
          this.$store.commit('setOfferComment', s)
        },
      },
      tags: {
        get () {
          return this.$store.state.offerTag
        },
        set (s) {
          this.$store.commit('setOfferTag', s)
        },
      },
      total: { // 总共多少页
        get () {
          return this.$store.state.offerTotal
        },
        set (s) {
          this.$store.commit('setOfferTotal', s)
        },
      },
      stared: {
        get () {
          return this.$store.state.offerStared
        },
        set (s) {
          this.$store.commit('setOfferStared', s)
        },
      },
    },
    watch: {
      pageNum: { // 页数一变动，就更新数据
        handler: function () {
          this.photo = []
          this.getOfferList()
          this.showingPanel = []
        },
      },
      moduleID: {
        handler: function () {
        },
      },
    },
    created () {
      this.photo = []
      this.getOfferList()
      this.refresh()
    },
    methods: {
      refresh () {
        this.pageNum = 1
        this.showingPanel = []
      },
      next () {
        ++this.pageNum
      },
      back () {
        --this.pageNum
      },
      getOfferList () {
        const moduleID = this.$store.state.currentModuleOfOffer
        this.moduleID = this.$store.state.currentModuleOfOffer

        const _this = this
        this.$axios.get('/module/' + moduleID + '/' + this.pageNum, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          const offers = res.data.data.page.records // 拿到的分页数据
          _this.$store.commit('setOfferList', offers)

          const allPages = res.data.data.page.pages // 总共多少页
          _this.$store.commit('setOfferTotal', allPages)

          const comments = res.data.data.comments
          _this.$store.commit('setOfferComment', comments)

          const tags = res.data.data.tags// 拿到当前tag
          _this.$store.commit('setOfferTag', tags)

          // 标星
          const star = res.data.data.stared
          _this.$store.commit('setOfferStared', star)

          // photo
          setTimeout(_this.getMinorPhoto, 1000)
        }).catch(err => {
          _this.$toast(err)
        })
      },
      getOfferDetail (i, offerID) {
        if (!this.showingPanel.includes(i)) {
          console.log('show post ' + i)
          // 查找post(i)的详细内容
        } else {
          // console.log('delete ' + i)
        }
      },
      srcoll () {
        setTimeout(() => {
          document.getElementById('nav').scrollIntoView(true)
        }, 700)
      },
      addAComment (i) { // 打开关闭评论
        this.offerListConfig[i].addComment = !this.offerListConfig[i].addComment
        this.offerListConfig[i].currentComment = ''
      },
      pushComment (i, offerID, comment) { // 发表评论
        if (localStorage.getItem('token') !== '') {
          if (comment !== '') {
            this.addCommentDTO.subjectId = offerID
            this.addCommentDTO.content = comment

            const _this = this
            this.$axios.post('/comment/add', this.addCommentDTO, {
              headers: {
                Authorization: localStorage.getItem('token'),
              },
            }).then(res => {
              _this.$toast(res.data.msg)
              this.offerListConfig[i].currentComment = ''
              this.getOfferList()
            }).catch(error => {
              _this.$toast(error)
            })
          }
        } else {
          alert('please login')
        }
      },
      cancelComment (i) { // 放弃评论
        this.offerListConfig[i].currentComment = ''
        this.addAComment(i) // 关闭评论窗口
      },
      showAllTheComments (i) {
        this.offerListConfig[i].showAllComments = !this.offerListConfig[i].showAllComments
      },
      starPost (index, offerID) {
        if (localStorage.getItem('token') !== '') {
          this.stared[index] = !this.stared[index]
          const _this = this
          if (this.stared[index]) {
            // 标星
            this.$axios.get('/star/add/' + offerID, {
              headers: {
                Authorization: localStorage.getItem('token'),
              },
            }).then(res => {
              _this.$toast(res.data.msg)
            })
          } else {
            // 取消标星
            this.$axios.get('/star/delete/' + offerID, {
              headers: {
                Authorization: localStorage.getItem('token'),
              },
            }).then(res => {
              _this.$toast(res.data.msg)
            })
          }
        } else {
          alert('please login')
        }
      },
      getMinorPhoto () {
        const offers = this.offers
        // console.log(offers)

        if (offers.length > 0) {
          const _this = this
          let id = ''
          for (let i = 0; i < offers.length; i++) {
            id = offers[i].publishUser
            this.$axios.post('/user/minorPhoto', id).then(res => {
              let base64Data = ''
              base64Data = String(res.data.data)
              _this.photo[i] = base64Data
            })
          }
        } else {
          console.log('loading photo')
          setTimeout(this.getMinorPhoto, 500)
        }
      },
    },
  }
</script>

<style scoped>
#inspire{
  background-color: #00695c;
}
</style>
