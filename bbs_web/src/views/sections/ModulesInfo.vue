<template>
  <base-section id="theme-features">
    <base-section-heading title="板块 topics">
      <v-container>
        <div style="text-align:center">
          <h3 style="color: #685454">
            'My roses are red,' it answered, 'as red as the feet of the dove,
          </h3>
          <h3 style="color: #7f6a6a">
            and redder than the great fans of coral that wave and wave in the ocean-cavern.
          </h3>
          <h3 style="color: #927d7d">
            But the winter has chilled my veins, and the frost has nipped my buds,
          </h3>
          <h3 style="color: #a19595">
            and the storm has broken my branches, and I shall have no roses at all this year.'
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
    name: 'SectionModulesInfo',

    data: () => ({
      features: [
        {
          title: '留学申请 Application',
          icon: 'mdi-fountain-pen-tip',
          text: 'Vienne la nuit sonne l’heure',
          dark: true,
          color: 'primary',
          id: 7,
        },
        {
          title: '签证移民 VISA & Immigration',
          icon: 'mdi-cellphone',
          text: 'J\'ai voulu ce matin te rapporter des roses',
          dark: false,
          color: '',
          id: 8,
        },
        {
          title: '海外生活 Local Experience',
          icon: 'mdi-pencil-box-outline',
          text: 'As sour as cat\'s breath,\n' +
            'harsh of tongue.',
          dark: false,
          color: '',
          id: 9,
        },
        {
          title: '本地求职 Job & Career',
          icon: 'mdi-image-size-select-actual',
          text: 'For sins no bleating victim can atone',
          dark: false,
          color: '',
          id: 10,
        },
        {
          title: '学习研究 Study & Research',
          icon: 'mdi-ice-pop',
          text: 'The Thames nocturne of blue and gold',
          dark: false,
          color: '',
          id: 11,
        },
        {
          title: '好物分享 Market & Deals',
          icon: 'mdi-help-circle-outline',
          text: 'That round and round the linden blossoms play',
          dark: false,
          color: '',
          id: 12,
        },
      ],
    }),
    methods: {
      searchModule (index, id) {
        this.$store.commit('changeCurrentModuleOfForum', id)
        this.$store.commit('changeCurrentTopicOfForum', this.features[index].title)
        this.highLightModule(index)
        // console.log('search for ' + id + ',content' + this.features[index].title)

        const _this = this
        this.$axios.get('/module/' + id + '/' + 1, { // 如果已登录，就有标星数据
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          console.log(res)
          // 拿到的分页数据
          const posts = res.data.data.page.records
          _this.$store.commit('setPostList', posts)
          // 拿到当前评论
          const comments = res.data.data.comments
          _this.$store.commit('setPostComment', comments)
          // 拿到当前tag
          const tags = res.data.data.tags
          _this.$store.commit('setPostTag', tags)
          // 总共多少页
          const allPages = res.data.data.page.pages
          _this.$store.commit('setPostTotal', allPages)
          // 标星
          const star = res.data.data.stared
          _this.$store.commit('setPostStared', star)
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
