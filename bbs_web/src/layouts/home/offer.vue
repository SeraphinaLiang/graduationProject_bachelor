<template>
  <v-container>
    <v-row>
      <!--------------------------offer 板块（地区）------------------------------>
      <v-col
        class="d-flex"
        cols="12"
        sm="6"
      >
        <v-select
          v-model="newOffer.selectPlace"
          :items="places"
          label="地区"
        />
      </v-col>
      <!--------------------------学位------------------------------>
      <v-col
        class="d-flex"
        cols="12"
        sm="6"
      >
        <v-select
          v-model="newOffer.selectDegree"
          :items="degrees"
          label="学历"
        />
      </v-col>
      <!----------------------------学校------------------------------>
      <v-col
        cols="12"
        sm="6"
        md="3"
      >
        <v-text-field
          v-model="newOffer.school"
          label="学校/机构"
        />
      </v-col>
      <!----------------------------专业----------------------------->
      <v-col
        cols="12"
        sm="6"
        md="3"
      >
        <v-text-field
          v-model="newOffer.major"
          label="专业"
        />
      </v-col>
      <!--------------------------timeline------------------------------------>
      <v-row>
        <!------------------------start time----------------------------------->
        <v-col
          cols="12"
          sm="6"
          md="4"
        >
          <v-menu
            v-model="menuStart"
            :close-on-content-click="false"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="newOffer.dateStart"
                label="start time"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              />
            </template>
            <v-date-picker
              v-model="newOffer.dateStart"
              @input="menuStart = false"
            />
          </v-menu>
        </v-col>
        <!------------------------end  time----------------------------------->
        <v-col
          cols="12"
          sm="6"
          md="4"
        >
          <v-menu
            v-model="menuEnd"
            :close-on-content-click="false"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="newOffer.dateEnd"
                label="end time"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              />
            </template>
            <v-date-picker
              v-model="newOffer.dateEnd"
              @input="menuEnd = false"
            />
          </v-menu>
        </v-col>
      </v-row>
    </v-row>
    <!------------------------Background----------------------------------->
    <v-container
      fluid
    >
      <v-textarea
        v-model="newOffer.background"
        outlined
        name="input-7-4"
        label="Your Background"
      />
    </v-container>
    <!-----------------------------TAG---------------------------------------->
    <h4>插入中文标签，请直接复制粘贴，然后按回车</h4>
    <v-combobox
      v-model="newOffer.chosenTags"
      style="margin-top: 10px"
      :items="allTags"
      chips
      clearable
      label="Add some tags (press enter)"
      multiple
      prepend-icon="mdi-filter-variant"
      solo
    >
      <template v-slot:selection="{ attrs, item, select, selected }">
        <v-chip
          v-bind="attrs"
          :input-value="selected"
          close
          @click="select"
          @click:close="remove(item)"
        >
          <strong>{{ item }}</strong>&nbsp;
        </v-chip>
      </template>
    </v-combobox>
    <!--------------------------BUTTON----------------------------------->
    <v-container>
      <v-btn
        style="margin-right: 5px;background-color: #00695c;width: 20px"
        color="white"
        class="text-center"
        text
        @click="ok"
      >
        PUSH
      </v-btn>
      <v-btn
        style="width: 20px"
        class="text-center"
        color="secondary"
        text
        @click="cancel"
      >
        Cancel
      </v-btn>
    </v-container>
  </v-container>
</template>

<script>
  export default {
    name: 'Offer',
    data: () => ({
      places: ['欧洲 Europe', '北美 north america', '亚洲 Asia', '澳洲 Australia', '香港 Hong kong', '英国 UK'],
      degrees: ['Bachelor', 'Master', 'Doctor', 'Other'],
      menuStart: false,
      menuEnd: false,
      allTags: ['TOEFL', 'GMAT', 'Finance', '2021', 'Computer Science', 'Ielts', 'GRE'],
      newOffer: {
        selectPlace: '',
        selectDegree: '',
        school: '',
        major: '',
        dateStart: new Date().toISOString().substr(0, 10),
        dateEnd: new Date().toISOString().substr(0, 10),
        background: '',
        chosenTags: [],
      },
    }),
    created () {
      this.clear()
    },
    methods: {
      clear () {
        this.newOffer.selectDegree = ''
        this.newOffer.selectPlace = ''
        this.newOffer.school = ''
        this.newOffer.major = ''
        this.newOffer.dateStart = new Date().toISOString().substr(0, 10)
        this.newOffer.dateEnd = new Date().toISOString().substr(0, 10)
        this.newOffer.background = ''
        this.newOffer.chosenTags = []
      },
      remove (item) {
        this.newOffer.chosenTags.splice(this.newOffer.chosenTags.indexOf(item), 1)
        this.newOffer.chosenTags = [...this.newOffer.chosenTags]
      },
      ok () {
        if (this.newOffer.selectPlace.length > 0 &&
          this.newOffer.selectDegree.length > 0 &&
          this.newOffer.school.length > 0 &&
          this.newOffer.major.length > 0 &&
          this.newOffer.background.length > 0 &&
          this.newOffer.chosenTags.length > 0 &&
          this.newOffer.dateStart.length > 0 &&
          this.newOffer.dateEnd.length > 0
        ) {
          const _this = this
          this.$axios.post('/offer/submit', JSON.stringify(this.newOffer), {
            headers: {
              Authorization: localStorage.getItem('token'),
            },
          }).then(res => {
            _this.$toast(res.data.msg)
          }).catch(error => {
            _this.$toast(error.msg)
          })
        } else {
          this.$toast('请完整填写本表格')
        }
      },
      cancel () {
        this.clear()
        this.$router.push('/')
      },
    },
  }
</script>

<style scoped>

</style>
