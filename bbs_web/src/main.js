import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import './plugins'
import 'es6-promise/auto'
import Vuex from 'vuex'
import axios from 'axios'
import toast from './components/message/toast'
import store from './store'
import './axios'
import './permission'

Vue.use(toast) // 全局挂载提示消息---调用 this.$toast(msg:'提示文字',time: 5000)
Vue.config.productionTip = false
Vue.use(Vuex)

Vue.prototype.$axios = axios

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')
