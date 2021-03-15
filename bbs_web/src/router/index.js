// Imports
import Vue from 'vue'
import Router from 'vue-router'
import Search from '@/views/sections/Search'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash }
    if (savedPosition) return savedPosition

    return { x: 0, y: 0 }
  },
  routes: [
    {
      path: '/',
      component: () => import('@/layouts/home/Index.vue'),
      children: [
        {
          path: '',
          name: 'Home',
          component: () => import('@/views/home/Index.vue'),
          children: [

          ],
        },
        {
          path: 'forum',
          name: 'Forum',
          component: () => import('@/views/forum/Index.vue'),
          meta: { src: require('@/assets/about.jpg') },
        },
        {
          path: 'offer',
          name: 'Offer',
          component: () => import('@/views/offer/Index.vue'),
          meta: { src: require('@/assets/contact.jpg') },
        },
        {
          path: 'user',
          name: 'User',
          component: () => import('@/views/user/Index.vue'),
          meta: {
            src: require('@/assets/pro.jpg'),
            requireAuth: true, // 需要登录
          },
        },
        // ------------------------------------------------
        {
          path: 'user/post/edit/:postId',
          name: 'UserPostEdit',
          component: () => import('@/views/user/userPostEditPage'),
          meta: {
            requireAuth: true, // 需要登录
          },
        },
        {
          path: 'search/:searchMsg',
          name: 'Search',
          component: () => import('@/views/home/SearchPage'),
        },
        {
          path: 'login',
          name: 'Login',
          component: () => import('@/views/user/loginPage'),
        },
        {
          path: 'signup',
          name: 'Signup',
          component: () => import('@/views/user/SignUpPage'),
        },
        {
          path: 'posting',
          name: 'Posting',
          component: () => import('@/views/forum/postEditPage'),
          meta: {
            requireAuth: true, // 需要登录
          },
        },
        {
          path: 'offerEdit',
          name: 'OfferEdit',
          component: () => import('@/views/offer/offerEditPage'),
          meta: {
            requireAuth: true, // 需要登录
          },
        },
        {
          path: '*',
          name: 'FourOhFour',
          component: () => import('@/views/404/Index.vue'),
        },
      ],
    },

  ],
})

export default router
