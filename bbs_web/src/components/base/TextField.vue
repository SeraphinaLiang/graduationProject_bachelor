<template>
  <v-text-field
    v-model="search"
    outlined
    dense
    single-line
    v-bind="$attrs"
  >
    <slot
      slot="append-outer"
      name="append-outer"
    />
  </v-text-field>
</template>

<script>
  export default {
    name: 'BaseTextField',
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
  }
</script>

<style lang="sass">
  //
</style>
