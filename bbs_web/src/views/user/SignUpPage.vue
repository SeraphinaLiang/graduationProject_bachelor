<template>
  <v-container style="width:900px;margin-top: 20px">
    <register />
  </v-container>
</template>
<script>
// Extensions
  import View from '@/views/View'
  // Mixins
  import Register from '@/layouts/home/register'

  export default {
    name: 'SignUpPage',
    components: {
      Register,
    },
    extends: View,
  }
</script>

<style scoped>

</style>
