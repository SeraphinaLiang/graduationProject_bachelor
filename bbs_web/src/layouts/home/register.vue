<template>
  <v-stepper v-model="e1">
    <v-stepper-header>
      <v-stepper-step
        :complete="e1 > 1"
        step="1"
      >
        Basic information
      </v-stepper-step>

      <v-divider />

      <v-stepper-step
        :complete="e1 > 2"
        step="2"
      >
        Validate your email
      </v-stepper-step>

      <v-divider />

      <v-stepper-step step="3">
        Successful!
      </v-stepper-step>
    </v-stepper-header>

    <v-stepper-items>
      <!-------------------------------------------basic info------------------------------------------------------------------->
      <v-stepper-content step="1">
        <v-card
          class="mb-12"
        >
          <v-list
            max-width="90%"
            style="margin-left: 35px"
            class="text-center"
          >
            <!--------------form------------------------>
            <validation-observer
              ref="observer"
              v-slot="{ invalid }"
            >
              <form
                :model="newUser"
                @submit.prevent="submit"
              >
                <!-------------username---------------->
                <validation-provider
                  v-slot="{ errors }"
                  name="Username"
                  rules="required|max:30|min:5"
                >
                  <v-text-field
                    v-model="newUser.username"
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
                  rules="required|max:30|min:1"
                >
                  <v-text-field
                    v-model="newUser.fullname"
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
                    v-model="newUser.email"
                    :error-messages="errors"
                    label="E-mail"
                    required
                  />
                </validation-provider>
                <!-------------password-1------------------->
                <validation-provider
                  v-slot="{ errors }"
                  rules="required|max:30|min:8"
                  name="password"
                >
                  <v-text-field
                    v-model="newUser.password1"
                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                    :type="show1 ? 'text' : 'password'"
                    label="Password"
                    :error-messages="errors"
                    :counter="30"
                    required
                    @click:append="show1 = !show1"
                  />
                </validation-provider>
                <!-------------password-2------------------->
                <validation-provider
                  v-slot="{ errors }"
                  rules="required|max:30|min:8"
                  name="repeated password"
                >
                  <v-text-field
                    v-model="newUser.password2"
                    :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                    :type="show2 ? 'text' : 'password'"
                    label="Repeat Password"
                    :error-messages="errors"
                    :counter="30"
                    required
                    @click:append="show2 = !show2"
                  />
                </validation-provider>
                <v-row style="margin-left: 30px;margin-bottom: 10px">
                  <v-btn
                    color="primary"
                    type="submit"
                    :disabled="invalid"
                    @click="stepOneContinue"
                  >
                    Continue
                  </v-btn>

                  <v-btn
                    v-if="showCancelButton"
                    text
                    @click="stepOneClose"
                  >
                    Cancel
                  </v-btn>
                </v-row>
              </form>
            </validation-observer>
          </v-list>
        </v-card>
      </v-stepper-content>
      <!-------------------------------------------validate email------------------------------------------------------------------>
      <v-stepper-content step="2">
        <v-card
          class="mb-12"
          color="white lighten-2"
          height="200px"
        >
          <v-col>
            <v-row>
              <h3 style="margin-left: 20px">
                The verification code has been sent to your email {{ newUser.email }}.
              </h3>
            </v-row>
            <v-row style="margin-top: 20px;margin-left: 20px">
              <v-col
                cols="24"
                sm="6"
              >
                <v-text-field
                  v-model="verificationCode"
                  label="Enter the verification code here"
                  hint="For example, SD83HE"
                  outlined
                />
              </v-col>
            </v-row>
          </v-col>
        </v-card>

        <v-btn
          color="primary"
          @click="stepTwoContinue"
        >
          Continue
        </v-btn>

        <v-btn
          v-if="showCancelButton"
          text
          @click="stepTwoClose"
        >
          Cancel
        </v-btn>
      </v-stepper-content>
      <!-------------------------------------------successful------------------------------------------------------------------->
      <v-stepper-content step="3">
        <v-card
          class="mb-12"
          color="white"
          height="200px"
        >
          <h2 style="color: #00695c;margin-top: 10px;margin-left: 20px">
            Your account has been created, please login with your email and password.
          </h2>
          <v-btn
            v-if="showCancelButton"
            text
            color="white"
            style="background-color: #00695c;margin-top: 30px;margin-left: 300px"
            align="right"
            @click="stepThreeClose"
          >
            To Login
          </v-btn>

          <!-- <v-dialog
            v-model="login"
            persistent
            max-width="500px"
          >
            <template v-slot:activator="{ on, attrs }">
              <base-btn
                text
                color="white"
                style="background-color: #00695c;margin-top: 30px;margin-left: 300px"
                align="right"
                v-bind="attrs"
                @click="stepThreeClose"
                v-on="on"
              >
                To Login
              </base-btn>
            </template>
            <login @CloseDialog="toogleLogin" />
          </v-dialog>-->
        </v-card>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>

<script>
  import { required, digits, email, max, regex, min } from 'vee-validate/dist/rules'
  import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
  import login from '@/layouts/home/login'
  import { mapState, mapMutations } from 'vuex'

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
    name: 'Register',
    components: {
      ValidationProvider,
      ValidationObserver,
      login,
    },
    data () {
      return {
        e1: 1, // 第几个stepper
        newUser: {
          username: '',
          fullname: '',
          email: '',
          password1: '',
          password2: '',
        },
        show1: true, // new 1
        show2: true, // new 2
        verificationCode: '',
        currentCode: '',
      }
    },
    methods: {
      ...mapMutations(['toogleLogin']),
      CloseDialog: function () {
        this.$store.commit('toogleSignUp')
        this.e1 = 1
        this.clear()
      },
      submit () {
        this.$refs.observer.validate()
      },
      clear () {
        this.newUser.username = ''
        this.newUser.fullname = ''
        this.newUser.email = ''
        this.newUser.password1 = ''
        this.newUser.password2 = ''
        this.newUser.verificationCode = ''
        this.$refs.observer.reset()
      },
      stepOneContinue () {
        const _this = this
        // 先检测该新用户是否合法
        this.$axios.post('/register/check', this.newUser).then(res => {
          alert(res.data.msg)
          _this.e1 = 2
          // 发送验证码
          _this.sendCode()
        }).catch(error => {
          alert(error)
        })
      },
      sendCode () {
        const _this = this
        this.$axios.post('/getCheckCode', _this.newUser.email).then(res => {
          _this.currentCode = res.data.msg
          console.log('验证码： ', res.data.msg)
        })
      },
      stepOneClose () {
        this.CloseDialog()
      },
      stepTwoContinue () {
        if (this.verificationCode === this.currentCode) {
          const _this = this
          this.$axios.post('/register', this.newUser).then(res => {
            alert(res.data.msg)
            this.e1 = 3
          }).catch(error => {
            alert(error)
          })
        } else {
          // console.log(this.currentCode)
          // console.log(this.verificationCode)
          alert('验证码错误')
        }
      },
      stepTwoClose () {
        this.CloseDialog()
      },
      stepThreeClose () { // to login
        this.CloseDialog()
        this.$toast('click the setting button')
      },
    },
    computed: {
      login: {
        get () {
          return this.$store.state.login
        },
        set () {
          this.$store.commit('toogleLogin')
        },
      },
      showCancelButton () {
        if (this.$route.path === '/signup') {
          return false
        } else {
          return true
        }
      },
    },
  }
</script>

<style scoped>

</style>
