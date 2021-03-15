export default {
  edit: false, // user page edit面板是否显示

  signUp: false, // setting 注册界面是否显示
  login: false, // setting  登录界面是否显示
  editer: false, // setting 发帖界面是否显示
  offer: false, // setting  offer界面是否显示
  chooseModules: false, // setting-editer-choose module 界面是否显示

  searchInfo: '', // 搜索内容

  // ---------------------------------

  postList: [], // forum界面初始化的帖子
  offerList: [], // offer界面初始化的帖子

  postComment: [], // forum的当前评论
  offerComment: [], // offer的当前评论

  postTag: [],
  offerTag: [],

  postTotal: 0,
  offerTotal: 0,

  postStared: [],
  offerStared: [],
  // ---------------------------------

  currentModuleOfForum: 0, // Forum 页面当前展示帖子的模块
  currentModuleOfOffer: 0, // Offer 页面当前展示帖子的模块

  currentTopicOfForum: 'University', // Forum 页面当前展示帖子的模块 topic
  currentTopicOfOffer: 'Europe', // Offer 页面当前展示帖子的模块 topic
}
