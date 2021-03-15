<template>
  <v-container style="margin-top: 10px">
    <h3 style="color: #00695c;margin: 10px">
      编辑帖子
    </h3>
    <editer />
  </v-container>
</template>
<script>
// Extensions
  import View from '@/views/View'
  import editer from '@/layouts/home/editer'

  export default {
    name: 'PostEditPageVue',
    components: {
      editer,
    },
    extends: View,
  }
</script>

<style scoped>

</style>
