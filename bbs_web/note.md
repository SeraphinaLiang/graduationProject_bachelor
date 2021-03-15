1. 全局挂载消息调用 ---this.$toast(msg:'提示文字',time: 5000)
组件在components/message里面

2. 用到state属性时，一定要这样写，不然会报错

     computed: {
       search: {
         get () {
           return this.$store.state.searchInfo
         },
         set (s) {
           this.$store.commit('setSearchInfo', s)
         },
       },
     },
    
    state:    searchInfo: '', // 搜索内容
    mutations:  setSearchInfo (state, info) {
                  state.searchInfo = info
                },

3. 了解一下搜索功能界面怎么实现 ，还剩用户主页没做完

4. form提交使用了 VeeValidate(Template Based Form Validation Framework for Vue.js)

5 . 照片上传功能待解决(看之前实习的项目)

6. 搜索关键词实时提示

7， forum和offer界面问题：1. star 拉取用户标星数据

8. 帖子修改功能待完成

9. 另一个项目用React做


