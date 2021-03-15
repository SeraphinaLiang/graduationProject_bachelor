export default {
  showEdit (state) { // user page--- edit面板是否显示
    state.edit = !state.edit
  },

  toogleSignUp (state) {
    state.signUp = !state.signUp
  },
  toogleLogin (state) {
    state.login = !state.login
  },
  closeLogin (state) {
    state.login = false
  },
  toogleEditer (state) {
    state.editer = !state.editer
  },
  toogleOffer (state) {
    state.offer = !state.offer
  },
  toogleChooseModules (state) {
    state.chooseModules = !state.chooseModules
  },

  setSearchInfo (state, info) {
    state.searchInfo = info
  },
  // -------------------------------
  setPostList (state, posts) {
    state.postList = posts
  },
  setOfferList (state, offers) {
    state.offerList = offers
  },

  setPostComment (state, postComment) {
    state.postComment = postComment
  },
  setOfferComment (state, offerComment) {
    state.offerComment = offerComment
  },

  setPostTag (state, postTag) {
    state.postTag = postTag
  },
  setOfferTag (state, offerTag) {
    state.offerTag = offerTag
  },

  setPostTotal (state, postTotal) {
    state.postTotal = postTotal
  },
  setOfferTotal (state, offerTotal) {
    state.offerTotal = offerTotal
  },

  setPostStared (state, postStared) {
    state.postStared = postStared
  },
  setOfferStared (state, offerStared) {
    state.offerStared = offerStared
  },

  // -------------------------------

  changeCurrentModuleOfForum (state, index) {
    state.currentModuleOfForum = index
  },
  changeCurrentModuleOfOffer (state, index) {
    state.currentModuleOfOffer = index
  },

  changeCurrentTopicOfForum (state, s) {
    state.currentTopicOfForum = s
  },
  changeCurrentTopicOfOffer (state, s) {
    state.currentTopicOfOffer = s
  },
}
