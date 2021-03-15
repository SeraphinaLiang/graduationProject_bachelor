import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8001'
axios.defaults.headers = { 'Content-type': 'application/json;charset=UTF-8' }

// 前置拦截
axios.interceptors.request.use(config => {
  return config
})

// 后置拦截
axios.interceptors.response.use(response => {
    const res = response.data

    if (res.code === 1) {
      // console.log('登录成功')
       console.log(res.msg)
      return response
    } else {
      // console.log('登陆失败:' + response.data.msg)
      console.log(res)
      return Promise.reject(response.data.msg)
    }
  },
  error => {
    // console.log(error)
    // if (error.response.data) {
    //   error.message = error.response.data.msg
    // }
    //
    // if (error.response.status === 401) {
    //   store.commit('REMOVE_INFO')
    //   router.push('/login')
    // }

    alert(error.response.data)
    return Promise.reject(error)
  },
)
