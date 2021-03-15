<template>
  <v-menu
    v-model="menu"
    :close-on-content-click="false"
    bottom
    left
    max-width="90%"
    min-width="275"
    offset-x
    offset-y
    origin="top right"
    transition="scale-transition"
    style="position:relative; z-index:1000;"
  >
    <template v-slot:activator="{ attrs, on }">
      <v-card
        id="settings"
        class="py-2 px-4"
        color="red accent-2"
        dark
        flat
        style="position: fixed; top: 164px; right: -35px;"
        width="100"
        v-bind="attrs"
        v-on="on"
      >
        <v-icon large>
          mdi-cog
        </v-icon>
      </v-card>
    </template>

    <v-card class="py-2">
      <base-title
        align="center"
        title="Write a post..."
        space="0"
      />
      <!----------------------------------POST----------------------------------------------->
      <v-card-text>
        <v-row
          justify="center"
          style="margin-top: 20px"
        >
          <base-btn
            :tile="false"
            rounded
            class="text-center"
            @click="toPosting"
          >
            Post
            <v-icon right>
              mdi-rocket
            </v-icon>
          </base-btn>
        </v-row>
        <!---------------------------------OFFER-------------------------------------------------->
        <v-row
          justify="center"
          style="margin-top: 20px"
        >
          <base-btn
            :tile="false"
            rounded
            class="text-center"
            @click="toOfferEdit"
          >
            Offer
            <v-icon right>
              mdi-rocket
            </v-icon>
          </base-btn>
        </v-row>
        <v-divider class="my-6" />
        <!---------------------------------SIGN UP------------------------------------------------>

        <v-row justify="center">
          <v-dialog
            v-model="signUp"
            persistent
            max-width="800px"
          >
            <template v-slot:activator="{ on, attrs }">
              <base-btn
                class="mb-6"
                color="accent"
                target="_blank"
                v-bind="attrs"
                v-on="on"
              >
                Sign Up
              </base-btn>
            </template>
            <v-card>
              <register @CloseDialog="toogleSignUp" />
            </v-card>
          </v-dialog>
        </v-row>

        <!------------------------------------LOGIN--------------------------------------------->
        <v-row justify="center">
          <v-dialog
            v-model="login"
            persistent
            max-width="500px"
          >
            <template v-slot:activator="{ on, attrs }">
              <base-btn
                color="#37474f"
                dark
                v-bind="attrs"
                v-on="on"
              >
                Login
              </base-btn>
            </template>
            <login @CloseDialog="toogleLogin" />
          </v-dialog>
        </v-row>
      </v-card-text>
    </v-card>
  </v-menu>
</template>
<script>
  import register from '@/layouts/home/register'
  import editer from '@/layouts/home/editer'
  import login from '@/layouts/home/login'
  import { mapState, mapMutations } from 'vuex'
  import offer from '@/layouts/home/offer'
  export default {
    name: 'HomeSettings',
    components: {
      offer,
      register,
      editer,
      login,
    },
    data () {
      return {
        menu: false,
        dialogPost: false,
      }
    },
    computed: {
      signUp: {
        get () {
          return this.$store.state.signUp
        },
        set () {
          this.$store.commit('toogleSignUp')
        },
      },
      login: {
        get () {
          return this.$store.state.login
        },
        set () {
          this.$store.commit('toogleLogin')
        },
      },
      editer: {
        get () {
          return this.$store.state.editer
        },
        set () {
          this.$store.commit('toogleEditer')
        },
      },
      offer: {
        get () {
          return this.$store.state.offer
        },
        set () {
          this.$store.commit('toogleOffer')
        },
      },
    },
    methods: {
      ...mapMutations(['toogleSignUp', 'toogleLogin', 'toogleEditer', 'toogleOffer']),
      toPosting () {
        this.$router.push('/posting',
                          com => { console.log(com) },
                          err => { console.log(err) })
      },
      toOfferEdit () {
        this.$router.push('/offerEdit',
                          com => { console.log(com) },
                          err => { console.log(err) })
      },
    },
  }
</script>
