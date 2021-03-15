import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default {
  namespace: true,

  state: {
    token: '',
    userInfo: JSON.parse(sessionStorage.getItem('userInfo')),
    bingo: false, // 是否已登录
  },
  mutations: {
    // set
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem('token', token)
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
      sessionStorage.setItem('userInfo', JSON.stringify(userInfo))
    },
    SET_BINGO: (state, bingo) => {
      state.bingo = bingo
      localStorage.setItem('bingo', '1')
    },
    REMOVE_INFO: (state) => {
      state.token = ''
      state.userInfo = {}
      state.bingo = false
      localStorage.setItem('token', '')
      localStorage.setItem('bingo', '0')
      sessionStorage.setItem('userInfo', JSON.stringify(''))
    },
  },
  getters: {
    // get
    getUser: state => {
      return state.userInfo
    },
    getBingo: state => {
      return state.bingo
    },
  },
  actions: {},
  modules: {},
}
