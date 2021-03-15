<template>
  <section id="hero">
    <v-img
      :min-height="minHeight"
      :src="require('@/assets/home-hero.jpg')"
      class="white--text"
      gradient="to right, rgba(5, 11, 31, .8), rgba(5, 11, 31, .8)"
    >
      <v-container class="fill-height px-4 py-12">
        <v-responsive
          class="d-flex align-center"
          height="100%"
          max-width="700"
          width="100%"
        >
          <base-heading title="A Study Abroad BBS" />
          <base-heading title="Dreams Up" />
          <base-body>
            本科毕业设计作品《BBS留学论坛》 华南农业大学-计算机科学与技术专业 <br>
            An undergraduate's graduation project in 2021 at South China Agricultural University
            to build up a BBS website.
            <br> 网站作者 THE Developer @ Yinqi Liang
            <br>
            <br> 首页仅用于展示项目功能结构和本项目所使用的技术
            <br> The project's documentation and the technologies stack are shown below.
          </base-body>

          <div
            :class="$vuetify.breakpoint.smAndDown ? 'flex-column align-start' : 'align-center'"
            class="d-flex flex-wrap"
          >
            <base-btn @click="signUpNow()">
              Sign up Now
            </base-btn>

            <span class="font-weight-bold ml-6 mr-4 my-4">or</span>

            <base-btn
              :ripple="false"
              class="pa-1"
              height="auto"
              text
              @click="discoverMore"
            >
              To login
            </base-btn>
          </div>
        </v-responsive>
      </v-container>
    </v-img>
  </section>
</template>

<script>
  import register from '@/layouts/home/register'
  import { mapMutations } from 'vuex'

  export default {
    name: 'SectionHero',
    components: {
      register,
    },
    provide: {
      theme: { isDark: true },
    },
    computed: {
      minHeight () {
        const height = this.$vuetify.breakpoint.mdAndUp ? '100vh' : '50vh'

        return `calc(${height} - ${this.$vuetify.application.top}px)`
      },
    },

    methods: {
      ...mapMutations(['toogleSignUp']),
      signUpNow () {
        this.$router.push('/signup')
      },
      discoverMore () {
        this.$router.push('/login')
      },
    },
  }
</script>
