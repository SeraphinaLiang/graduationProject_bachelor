<template>
  <v-card>
    <v-card-title>
      <span class="headline">Login</span>
    </v-card-title>
    <v-card-text>
      <v-container>
        <validation-observer
          ref="observer"
          v-slot="{ invalid }"
        >
          <form @submit.prevent="submit">
            <v-row>
              <v-col cols="10">
                <validation-provider
                  v-slot="{ errors }"
                  name="email"
                  rules="required|email"
                >
                  <v-text-field
                    v-model="loginData.email"
                    :error-messages="errors"
                    label="E-mail"
                    required
                  />
                </validation-provider>
                <validation-provider
                  v-slot="{ errors }"
                  rules="required|max:30|min:8"
                  name="password"
                >
                  <v-text-field
                    v-model="loginData.password"
                    :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                    :type="show ? 'text' : 'password'"
                    label="Password"
                    :error-messages="errors"
                    :counter="30"
                    required
                    @click:append="show = !show"
                  />
                </validation-provider>
              </v-col>
            </v-row>
            <v-row>
              <v-btn
                color="deep-orange"
                text
                x-small
                @click="forgetPassword"
              >
                Forget password?
              </v-btn>
              <v-spacer />
              <v-btn
                color="blue darken-1"
                text
                type="submit"
                :disabled="invalid"
              >
                Confirm
              </v-btn>
              <v-btn
                v-if="showCancelButton"
                color="blue darken-1"
                text
                @click="cancel"
              >
                Cancel
              </v-btn>
            </v-row>
          </form>
        </validation-observer>
      </v-container>
    </v-card-text>
  </v-card>
</template>

<script>
  import { required, digits, email, max, regex, min } from 'vee-validate/dist/rules'
  import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
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
    name: 'Login',
    components: {
      ValidationProvider,
      ValidationObserver,
    },
    data () {
      return {
        loginData: {
          email: '',
          password: '',
        },
        show: false,
      }
    },
    computed: {
      showCancelButton () {
        if (this.$route.path === '/login') {
          return false
        } else {
          return true
        }
      },
    },
    methods: {
      ...mapMutations(['toogleLogin']),
      forgetPassword () {

      },
      submit () {
        this.$refs.observer.validate().then(success => {
          const _this = this // 先把this保存起来

          if (_this.$store.getters.getBingo === true) {
            alert('您已登录，请勿重复登陆')
            return
          }
          if (!success) {
            return
          }
          this.$axios.post('/login', this.loginData).then(res => {
            console.log(res)
            const jwt = res.headers.authorization
            const userInfo = res.data.data

            // 把数据共享出去
            _this.$store.commit('SET_TOKEN', jwt)
            _this.$store.commit('SET_USERINFO', userInfo)
            _this.$store.commit('SET_BINGO', true)

            // 如果在setting处打开，则关闭登录弹窗
            _this.$store.commit('closeLogin')
            // 获取
            // console.log(_this.$store.getters.getUser)

            _this.$router.push('/user')
          })
        })
      },
      cancel () {
        this.toogleLogin()
        this.$refs.observer.reset()
        this.loginData.email = ''
        this.loginData.password = ''
      },
    },
  }
</script>

<style scoped>

</style>
