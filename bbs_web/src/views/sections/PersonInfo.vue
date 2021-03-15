<template>
  <div>
    <v-container>
      <v-row>
        <v-col cols="auto">
          <div>
            <img
              :src="'data:image/jpg;base64,'+imgSrc"
              alt="not available"
              width="250px"
            >
            <v-card
              max-width="250px"
              style="margin-bottom: 20px"
            >
              <v-card-title>#{{ userBasicInfo.username }}</v-card-title>

              <v-card-text>
                <div>Hello, {{ userBasicInfo.fullname }} </div>
                <div>{{ userBasicInfo.email }}</div>
              </v-card-text>
            </v-card>

            <!--------------------------------edit---------------------------------------------->
            <v-dialog
              v-model="edit"
              fullscreen
              hide-overlay
              transition="dialog-bottom-transition"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  tile
                  color="#00695c"
                  class="white--text"
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon left>
                    mdi-pencil
                  </v-icon>
                  Edit
                </v-btn>
              </template>
              <personal-info-edit @CloseDialog="showEdit" />
            </v-dialog>
            <!---------------logout-------------------->
            <v-btn
              style="margin: 5px"
              tile
              color="secondary"
              class="white--text"
              @click="logout"
            >
              Logout
            </v-btn>
          </div>
        </v-col>
        <!--------------------------------right---------------------------------------------->
        <v-col>
          <v-card
            color="basil"
            min-height="600px"
          >
            <v-tabs
              v-model="tab"
              background-color="#00695c"
              color="white"
              dark
              grow
            >
              <v-tab>My Posts</v-tab>
              <v-tab>My Offers</v-tab>
              <v-tab>Star</v-tab>
              <v-tab>Comments</v-tab>
              <!-------------my post------------>
              <v-tab-item>
                <v-card
                  color="basil"
                  flat
                >
                  <v-card-text>
                    <user-post />
                  </v-card-text>
                </v-card>
              </v-tab-item>
              <!-------------My Offers------------>
              <v-tab-item>
                <v-card
                  color="basil"
                  flat
                >
                  <v-card-text>
                    <user-offer />
                  </v-card-text>
                </v-card>
              </v-tab-item>
              <!-------------star-------------->
              <v-tab-item>
                <v-card
                  color="basil"
                  flat
                >
                  <v-card-text>
                    <user-star />
                  </v-card-text>
                </v-card>
              </v-tab-item>
              <!--------------comments------------->
              <v-tab-item>
                <v-card
                  color="basil"
                  flat
                >
                  <v-card-text>
                    <user-comment />
                  </v-card-text>
                </v-card>
              </v-tab-item>
              <!-------------end---------------->
            </v-tabs>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
  import PersonalInfoEdit from '@/layouts/home/PersonalInfoEdit'
  import userComment from '@/views/sections/userEdit/userComment'
  import userOffer from '@/views/sections/userEdit/userOffer'
  import userStar from '@/views/sections/userEdit/userStar'
  import userPost from '@/views/sections/userEdit/userPost'
  import { mapState, mapMutations } from 'vuex'

  export default {
    name: 'SectionPersonInfo',
    components: {
      PersonalInfoEdit,
      userStar,
      userPost,
      userOffer,
      userComment,
    },
    data () {
      return {
        tab: null,
        id: '',
        imgSrc: '',
        userBasicInfo: {
          username: '',
          fullname: '',
          email: '',
        },
      }
    },
    computed: {
      edit: {
        get () {
          return this.$store.state.edit
        },
        set () {
          this.$store.commit('showEdit')
        },
      },
    },
    watch: {
      edit: {
        handler: function () {
          this.initData()
        },
      },
    },
    created () {
      this.initData()
    },
    methods: {
      ...mapMutations(['showEdit']),
      logout () {
        const _this = this
        this.$axios.get('/logout',
                        {
                          headers: {
                            Authorization: localStorage.getItem('token'),
                          },
                        },
        ).then(response => {
          _this.$store.commit('REMOVE_INFO')
          _this.$router.push('/')
          _this.$toast(response.data.msg)
          // alert(response.data.msg)
        })
      },
      initData () {
        const _this = this
        this.$axios.get('/user/info', {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          const user = res.data.data
          _this.userBasicInfo.email = user.email
          _this.userBasicInfo.fullname = user.fullname
          _this.userBasicInfo.username = user.username
          _this.id = user.id

          _this.getPhoto()
        })
      },
      getPhoto () {
        const _this = this
        const id = this.id
        this.$axios.post('/user/photo', id, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          const base64Data = res.data.data
          _this.imgSrc = base64Data
        })
      },
    },
  }
</script>

<style scoped>
/* Helper classes */
.basil {
  background-color: white !important;
}
.basil--text {
  color: #356859 !important;
}
</style>
