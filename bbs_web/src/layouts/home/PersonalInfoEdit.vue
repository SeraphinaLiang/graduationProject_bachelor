<template>
  <v-card>
    <v-toolbar
      dark
      color="accent"
    >
      <v-btn
        icon
        dark
        @click="toogleEdit"
      >
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>Edit</v-toolbar-title>
      <v-spacer />
    </v-toolbar>
    <v-list
      style="max-width: 80% ;margin-left: 40px"
      three-line
      subheader
    >
      <v-subheader>Core Information</v-subheader>
      <!-----------photo------------------------>
      <img
        :src="'data:image/jpg;base64,'+imgSrc"
        alt="not available"
        height="200px"
        style="margin-top: 15px"
      >
      <form
        enctype="multipart/form-data"
      >
        <div style="margin-top: 10px">
          <input
            accept="image/png, image/jpeg, image/jpg"
            placeholder="Pick a photo (only .png .jpeg .jpg are accepted)"
            type="file"
            show-size
            prepend-icon="mdi-camera"
            @change="selectPhoto"
          >
        </div>
        <div>
          <v-btn
            tile
            color="#00695c"
            class="white--text"
            style="margin-top: 15px ; margin-bottom: 15px"
            @click="uploadPhoto"
          >
            <v-icon left>
              mdi-pencil
            </v-icon>
            Upload
          </v-btn>
        </div>
      </form>
      <!--------------form------------------------>
      <validation-observer
        ref="observer"
        v-slot="{ invalid }"
      >
        <form
          :model="userBasicInfo"
          @submit.prevent="submit"
        >
          <!-------------username---------------->
          <validation-provider
            v-slot="{ errors }"
            name="Username"
            rules="required|max:30|min:2"
          >
            <v-text-field
              v-model="userBasicInfo.username"
              :counter="30"
              :error-messages="errors"
              label="Username"
              required
            />
          </validation-provider>
          <!-------------fullname---------------->
          <validation-provider
            v-slot="{ errors }"
            name="Fullname"
            rules="required|max:30|min:2"
          >
            <v-text-field
              v-model="userBasicInfo.fullname"
              :counter="30"
              :error-messages="errors"
              label="Full Name"
              required
            />
          </validation-provider>
          <!-------------email---------------->
          <validation-provider
            v-slot="{ errors }"
            name="email"
            rules="required|email"
          >
            <v-text-field
              v-model="userBasicInfo.email"
              disabled
              :error-messages="errors"
              label="E-mail"
              required
            />
          </validation-provider>
          <!----------- gender------------------>
          <validation-provider
            v-slot="{ errors }"
            name="gender"
          >
            <v-select
              v-model="gender"
              :items="genderChoices"
              :error-messages="errors"
              label="Gender"
              data-vv-name="gender"
            />
          </validation-provider>
          <!-----------button--------------->
          <v-btn
            class="mr-4"
            type="submit"
            :disabled="invalid"
            @click="changeUserInfo"
          >
            confirm
          </v-btn>
          <v-btn @click="clear">
            reset
          </v-btn>
        </form>
      </validation-observer>
    </v-list>
    <v-divider style="margin-right: 40px ;margin-left: 40px" />
    <v-list
      style="max-width: 80% ;margin-left: 40px"
      three-line
      subheader
    >
      <v-subheader>Change Password</v-subheader>
      <!-------------change-password-------------------->
      <validation-observer
        ref="observerPassword"
        v-slot="{ invalid }"
      >
        <form
          :model="password"
          @submit.prevent="submitPassword"
        >
          <!-------------old-password-------------------->
          <validation-provider
            v-slot="{ errors }"
            rules="required|max:30|min:8"
            name="Old password"
          >
            <v-text-field
              v-model="password.old"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :type="show1 ? 'text' : 'password'"
              label="Old Password"
              :error-messages="errors"
              :counter="30"
              required
              @click:append="show1 = !show1"
            />
          </validation-provider>
          <!-------------new-password-1------------------->
          <validation-provider
            v-slot="{ errors }"
            rules="required|max:30|min:8"
            name="New password 1"
          >
            <v-text-field
              v-model="password.new1"
              :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
              :type="show2 ? 'text' : 'password'"
              label="New Password"
              :error-messages="errors"
              :counter="30"
              required
              @click:append="show2 = !show2"
            />
          </validation-provider>
          <!-------------new-password-2------------------->
          <validation-provider
            v-slot="{ errors }"
            rules="required|max:30|min:8"
            name="New password 2"
          >
            <v-text-field
              v-model="password.new2"
              :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
              :type="show3 ? 'text' : 'password'"
              label="Repeat New Password"
              :error-messages="errors"
              :counter="30"
              required
              @click:append="show3 = !show3"
            />
          </validation-provider>
          <!-------------confirm message------------------->
          <v-container style="max-width: 50% ;margin-left: 0">
            <div>
              <v-btn
                tile
                color="#00695c"
                class="white--text"
                type="submit"
                :disabled="invalid"
                @click="changePassword"
              >
                <v-icon left>
                  mdi-pencil
                </v-icon>
                Confirm
              </v-btn>
              <v-btn
                tile
                color="#00695c"
                class="white--text"
                style="margin-left: 15px"
                @click="clearPasswordFields"
              >
                <v-icon left>
                  mdi-pencil
                </v-icon>
                CLEAR
              </v-btn>
              <!--message box for this page-->
              <v-snackbar
                v-model="snackbar"
                color="primary"
              >
                {{ msg }}
                <v-btn
                  color="accent"
                  text
                  @click="snackbar = false"
                >
                  Close
                </v-btn>
              </v-snackbar>
            </div>
          </v-container>
        </form>
      </validation-observer>
    </v-list>
  </v-card>
</template>

<script>
  import { required, digits, email, max, regex, min } from 'vee-validate/dist/rules'
  import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'

  setInteractionMode('eager')

  extend('digits', {
    ...digits,
    message: '{_field_} needs to be {length} digits. ({_value_})',
  })

  extend('required', {
    ...required,
    message: '{_field_} can not be empty',
  })

  extend('max', {
    ...max,
    message: '{_field_} may not be greater than {length} characters',
  })

  extend('min', {
    ...min,
    message: '{_field_} may not be fewer than {length} characters',
  })

  extend('regex', {
    ...regex,
    message: '{_field_} {_value_} does not match {regex}',
  })

  extend('email', {
    ...email,
    message: 'Email must be valid',
  })

  export default {
    name: 'PersonalInfoEdit',
    components: {
      ValidationProvider,
      ValidationObserver,
    },
    data () {
      return {
        userID: '',
        userBasicInfo: {
          username: '',
          fullname: '',
          email: '',
          genderNumber: -1,
        },
        gender: null,
        genderChoices: [
          'Female',
          'Male',
          'Others',
          'Prefer not to tell',
        ],
        show1: true, // old
        show2: false, // new 1
        show3: false, // new 2
        password: {
          old: '',
          new1: '',
          new2: '',
        },
        snackbar: false,
        msg: '',
        rules: [
          value => !value || value.size < 2000000 || 'Photo size should be less than 2 MB!',
        ],
        photo: '', // choose photo
        imgSrc: '',
      }
    },
    created () {
      this.initData()
      this.password.old = ''
      this.password.new1 = ''
      this.password.new2 = ''
    },
    mounted () {
      this.clearPasswordFields()
    },
    methods: {
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
          _this.gender = _this.genderChoices[user.gender]
          _this.userID = user.id
          // get photo
          _this.getPhoto()
        })
      },
      toogleEdit () { // 打开关闭本窗口
        this.$store.commit('showEdit')
      },
      submit () { // 满足限制前按钮不可用
        this.$refs.observer.validate()
      },
      clear () {
        this.initData()
        this.$refs.observer.reset()
      },
      // 提交修改密码申请
      changePassword () {
        const _this = this
        this.$axios.post('/user/edit/password', this.password, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          // console.log(res.data.msg)
          _this.showMsg(res.data.msg)
        }).catch(error => {
          _this.showMsg(error)
          // console.log(error.msg)
        })
      },
      clearPasswordFields () {
        this.password.old = ''
        this.password.new1 = ''
        this.password.new2 = ''
        this.$refs.observerPassword.reset()
      },
      submitPassword () { // 满足限制前按钮不可用
        this.$refs.observerPassword.validate()
      },
      // 修改用户基本信息
      changeUserInfo () {
        if (this.gender === this.genderChoices[0]) { // 判断gender
          this.userBasicInfo.genderNumber = 0
        } else if (this.gender === this.genderChoices[1]) {
          this.userBasicInfo.genderNumber = 1
        } else if (this.gender === this.genderChoices[2]) {
          this.userBasicInfo.genderNumber = 2
        } else if (this.gender === this.genderChoices[3]) {
          this.userBasicInfo.genderNumber = 3
        }

        const _this = this // 先把this保存起来
        this.$axios.post('/user/edit', this.userBasicInfo, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          console.log(res)
          _this.showMsg('Your profile has been changed.')
        })
      },
      // 这个界面的消息提示
      showMsg (msg) {
        this.msg = msg
        this.snackbar = true
      },
      uploadPhoto () {
        if (this.photo !== '') {
          const _this = this
          const formData = new FormData()
          formData.append('photo', _this.photo)
          this.$axios.post('/user/upload/photo', formData, {
            headers: {
              Authorization: localStorage.getItem('token'),
              'Content-Type': 'multipart/form-data',
            },
          }).then(res => {
            _this.showMsg(res.data.msg)
            _this.photo = ''
            // refresh photo
            _this.getPhoto()
            // eslint-disable-next-line handle-callback-err
          }).catch(error => {
            _this.showMsg('photo too big! 图片过大！ ')
            // console.log(error.msg)
          })
        } else {
          this.showMsg('please choose a photo')
        }
      },
      selectPhoto (e) {
        this.photo = e.target.files[0]
        // console.log(this.photo)
      },
      getPhoto () {
        const _this = this
        const id = this.userID
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

</style>
