<template>
  <v-container style="width: 600px;margin-top: 50px">
    <login />
  </v-container>
</template>
<script>
  // Extensions
  import View from '@/views/View'
  // Mixins
  import login from '@/layouts/home/login'

  export default {
    name: 'LoginPage',
    components: {
      login,
    },
    extends: View,
  }
</script>

<style scoped>

</style>
