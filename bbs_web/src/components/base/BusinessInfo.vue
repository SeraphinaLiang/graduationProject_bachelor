<template>
  <v-theme-provider :dark="dark">
    <div>
      <v-img
        :src="require('@/assets/myphoto.jpg')"
      />
      <base-info-card
        title="Yinqi Liang, the developer"
        pace="6"
      >
        Bachelor of Science in Computer Science and Technology<br>
        # South china Agricultural University (Bachelor)<br>
        # University College Dublin (Exchange)<br><br>
        CSC Scholarship Winner in 2018<br>
        Major: Computer Science & Minor: Biological Science<br>
        Language: IELTS (7.5) & French (B2)<br><br>
        Also have a great passion for ballet and learning languages !
      </base-info-card>

      <base-business-contact dense />
    </div>
  </v-theme-provider>
</template>

<script>
  export default {
    name: 'BaseBusinessInfo',

    props: { dark: Boolean },

    data: () => ({
      business: [
        {
          icon: 'mdi-map-marker-outline',
          title: 'Address',
          text: '8553 N. Beach St. Ste. 227<br>Fort Worth, Texas 76137',
        },
        {
          icon: 'mdi-cellphone',
          title: 'Phone',
          text: '01 (800) 433 744<br>01 (800) 433 633',
        },
        {
          icon: 'mdi-email',
          title: 'Email',
          text: 'john@vuetifyjs.com<br>heather@vuetifyjs.com',
        },
      ],
    }),
  }
</script>
