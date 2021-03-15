<template>
  <v-container style="margin-top: 10px">
    <h3 style="color: #00695c;margin: 10px">
      编辑offer
    </h3>
    <offer />
  </v-container>
</template>

<script>
  import View from '@/views/View'
  import offer from '@/layouts/home/offer'

  export default {
    name: 'OfferEditPageVue',
    components: {
      offer,
    },
    extends: View,
  }
</script>

<style scoped>

</style>
