<template>
  <v-list>
    <template v-for="(item, index) in myPostList">
      <v-list-item :key="index+'a'">
        <v-list-item-content>
          <v-list-item-subtitle v-text="'#'+(index+1)" />
          <v-container>
            <h3>{{ item.title }}</h3>
            <div>
              <h4>{{ item.briefing }}</h4>
            </div>
            <div
              style="margin-top: 10px"
              v-html="item.detail"
            />
          </v-container>
        </v-list-item-content>
        <!-------------------------------------------------->
        <v-tooltip top>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              elevation="2"
              icon
              x-small
              v-bind="attrs"
              v-on="on"
              @click="deleteStar(item.id)"
            >
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </template>
          <span>Cancel Star</span>
        </v-tooltip>
      </v-list-item>

      <v-divider
        :key="index"
      />
    </template>
  </v-list>
</template>

<script>
  export default {
    name: 'UserStarVue',
    data () {
      return {
        myPostList: [],
      }
    },
    created () {
      this.initData()
    },
    methods: {
      deleteStar (index) {
        const _this = this
        this.$axios.get('/user/delete/star?subjectID=' + index, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          _this.$toast(res.data.msg)
          _this.initData()
        })
      },
      initData () {
        const _this = this
        this.$axios.get('/user/star/get', {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }).then(res => {
          console.log(res.data.data)
          _this.myPostList = res.data.data
        })
      },
    },
  }
</script>

<style scoped>

</style>
