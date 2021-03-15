<template>
  <v-container style="margin-top: 10px">
    <template>
      <v-card
        class="mx-auto"
      >
        <v-toolbar
          color="deep-purple accent-1"
          dark
        >
          <v-toolbar-title>Search Results</v-toolbar-title>
          <v-spacer />
        </v-toolbar>

        <v-list two-line>
          <v-list-item-group
            v-model="selected"
            active-class="pink--text"
            multiple
          >
            <template v-for="(item, index) in items">
              <v-list-item :key="index+'k'">
                <template v-slot:default="{ active }">
                  <v-list-item-content>
                    <v-list-item-title v-text="item.title" />

                    <v-list-item-subtitle
                      class="text--primary"
                      v-text="item.briefing"
                    />

                    <v-list-item-subtitle>
                      <div v-html="item.detail" />
                    </v-list-item-subtitle>
                  </v-list-item-content>

                  <v-list-item-action>
                    <v-list-item-action-text v-text="item.createTime" />
                  </v-list-item-action>
                </template>
              </v-list-item>

              <v-divider
                v-if="index < items.length - 1"
                :key="index"
              />
            </template>
          </v-list-item-group>
        </v-list>
      </v-card>
    </template>
  </v-container>
</template>

<script>
  import View from '@/views/View'

  export default {
    name: 'SearchPageVue',
    extends: View,
    data: () => ({
      selected: [],
      items: [],
    }),
    computed: {
      searchMsg: {
        get () {
          return this.$store.state.searchInfo
        },
        set (s) {
          this.$store.commit('setSearchInfo', s)
        },
      },
    },
    created () {
      this.search()
    },
    methods: {
      search () {
        console.log('router: ' + this.$route.params.searchMsg)
        if (this.$route.params.searchMsg !== '') {
          const _this = this
          this.$axios.get('/search/' + this.$route.params.searchMsg).then(res => {
            // console.log(res.data.data)
            _this.items = res.data.data
          })
        }
      },
    },
  }
</script>

<style scoped>

</style>
