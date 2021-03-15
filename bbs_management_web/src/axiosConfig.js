import axios from 'axios'
// 跨域请求
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
       // console.log(res.msg)
      return response
    } else {
      // console.log(res)
      return Promise.reject(response.data.msg)
    }
  },
  error => {
    alert(error.response.data)
    return Promise.reject(error)
  },
)
