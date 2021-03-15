import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      component: () => import('@/views/dashboard/Index'),
      children: [
        // Dashboard
        {
          name: 'Dashboard',
          path: '',
          component: () => import('@/views/dashboard/Dashboard'),
        },
        // Pages
        {
          name: 'Users Statistics',
          path: 'userStatistics',
          component: () => import('@/views/dashboard/pages/UserProfile'),
        },
        {
          name: 'Messages',
          path: 'messages',
          component: () => import('@/views/dashboard/component/Notifications'),
        },
        {
          name: 'Comment Views',
          path: 'commentView',
          component: () => import('@/views/dashboard/component/Typography'),
        },
        {
          name: 'Posts Table',
          path: 'postsTable',
          component: () => import('@/views/dashboard/tables/RegularTables'),
        },
      ],
    },
  ],
})
