<template>
  <base-section id="theme-features">
    <base-section-heading title="地区 zone">
      <v-container>
        <div style="text-align:center">
          <h3 style="color: #3d5a47">
            Shall I compare thee to a summer's day?
          </h3>
          <h3 style="color: #547660">
            Thou art more lovely and more temperate.
          </h3>
          <h3 style="color: #698b75">
            Rough winds do shake the darling buds of May,
          </h3>
          <h3 style="color: #8fa896">
            And summer's lease hath all too short a date.
          </h3>
        </div>
      </v-container>
    </base-section-heading>

    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in features"
          :key="i"
          cols="12"
          md="6"
        >
          <v-row>
            <div
              style="min-width: min-content ;min-height:min-content; cursor: pointer"
              @click="searchModule(i,feature.id)"
            >
              <v-col>
                <base-avatar-card
                  v-bind="feature"
                  align="left"
                  horizontal
                />
              </v-col>
            </div>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  export default {
    name: 'SectionCountry',

    data: () => ({
      features: [
        {
          title: '欧洲 Europe',
          icon: 'mdi-fountain-pen-tip',
          text: 'Sometime too hot the eye of heaven shines',
          dark: true,
          color: 'primary',
          id: 1,
        },
        {
          title: '北美 North America',
          icon: 'mdi-cellphone',
          text: 'And often is his gold complexion dimmed',
          dark: false,
          color: '',
          id: 2,
        },
        {
          title: '亚洲 Asia',
          icon: 'mdi-pencil-box-outline',
          text: 'And every fair from fair sometime declines',
          dark: false,
          color: '',
          id: 3,
        },
        {
          title: '澳洲 Australia',
          icon: 'mdi-image-size-select-actual',
          text: 'By chance, or nature\'s changing course untrimmed',
          dark: false,
          color: '',
          id: 4,
        },
        {
          title: '香港 Hong kong',
          icon: 'mdi-ice-pop',
          text: 'But thy eternal summer shall not fade',
          dark: false,
          color: '',
          id: 5,
        },
        {
          title: '英国 UK',
          icon: 'mdi-help-circle-outline',
          text: 'Nor lose possession of that fair thou ow\'st',
          dark: false,
          color: '',
          id: 6,
        },
      ],
    }),
    methods: {
      searchModule (index, id) {
        this.$store.commit('changeCurrentModuleOfOffer', id)
        this.$store.commit('changeCurrentTopicOfOffer', this.features[index].title)
        this.highLightModule(index)
        // console.log('search for ' + id + ',content' + this.features[index].title)

        const _this = this
        this.$axios.get('/module/' + id + '/' + 1, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          // 拿到的分页数据
          const offers = res.data.data.page.records
          _this.$store.commit('setOfferList', offers)
          // 拿到当前评论
          const comments = res.data.data.comments
          _this.$store.commit('setOfferComment', comments)
          // 拿到当前tag
          const tags = res.data.data.tags
          _this.$store.commit('setOfferTag', tags)
          // 总共多少页
          const allPages = res.data.data.page.pages
          _this.$store.commit('setOfferTotal', allPages)
          // 标星
          const star = res.data.data.stared
          _this.$store.commit('setOfferStared', star)
        }).catch(err => {
          _this.$toast(err)
        })
      },
      highLightModule (index) {
        this.features[index].dark = true // 高亮当前模块
        this.features[index].color = 'primary'
        // 没点的不高亮
        const len = this.features.length
        for (var i = 0; i < len; i++) {
          if (i !== index) {
            this.features[i].dark = false
            this.features[i].color = ''
          }
        }
      },
    },
  }
</script>
