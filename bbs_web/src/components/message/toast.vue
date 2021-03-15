<template>
  <!-- 全局提示框 -->
  <transition
    id="box"
    name="slide-fade"
  >
    <div
      v-if="visible"
      class="dialog-tips"
    >
      <div>{{ message }}</div>
    </div>
  </transition>
</template>
<script>
  export default {
    data () {
      return {
        visible: false,
        message: '',
      }
    },
  }
</script>
<style scoped>
#box{
  z-index: 10000000;
}
.dialog-tips{
  min-width: 380px;
  box-sizing: border-box;
  border-radius: 3px;
  border: 0px solid #e1f3d8;
  position: fixed;
  left: 50%;
  top: 90%;
  transform: translateX(-50%);
  background-color: #f0f9eb;
  overflow: hidden;
  padding: 15px 15px 15px 20px;
  display: flex;
  align-items: center;
  opacity: 90%;
  background-color: #00695c;
  color: #ffffff;
  font-family: sans-serif;
}

.slide-fade-enter, .slide-fade-leave-to {
  margin-top:-30px;
  opacity: 0;

}

.slide-fade-enter-active,.slide-fade-leave-active {
  transition: all .3s ease;
}

.slide-fade-enter-to,.slide-fade-leave {
  margin-top:0px;
  opacity:1;
}
</style>
