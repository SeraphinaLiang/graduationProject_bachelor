<template>
  <v-theme-provider dark>
    <base-section
      id="contact-us"
      class="primary"
      space="36"
    >
      <v-row
        align="center"
        class="ma-0"
        justify="center"
      >
        <base-title
          space="0"
          title="Search For Anything"
        />

        <v-responsive
          class="px-4 mx-6"
          max-width="600"
          width="100%"
        >
          <base-text-field
            hide-details
            label="Universities / Countries / Users ... "
          >
            <template v-slot:append-outer>
              <v-btn
                class="ml-n1"
                height="40"
                outlined
                style="margin-top: -7px;"
                @click="search"
              >
                JUMP
              </v-btn>
            </template>
          </base-text-field>
        </v-responsive>
      </v-row>
    </base-section>
  </v-theme-provider>
</template>

<script>
  export default {
    name: 'SectionSearchAlt',
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
    methods: {
      search () {
        console.log(this.searchMsg)
        if (this.searchMsg !== '') {
          this.$router.push('/search/' + this.searchMsg)
        }
      },
    },
  }
</script>
