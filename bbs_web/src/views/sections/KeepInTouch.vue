<template>
  <base-section
    id="info-alt"
    space="56"
  >
    <v-container>
      <v-row justify="space-between">
        <v-col
          cols="12"
          md="5"
        >
          <base-business-info />
        </v-col>

        <v-col
          cols="12"
          md="6"
        >
          <div>
            <base-info-card
              :title="title"
              :subtitle="subtitle"
              space="4"
              color="primary"
            />

            <v-text-field
              v-model="contact.name"
              outlined
              dense
              single-line
              label="Name"
            />

            <v-text-field
              v-model="contact.email"
              outlined
              dense
              single-line
              label="Email"
            />

            <v-text-field
              v-model="contact.subject"
              outlined
              dense
              single-line
              label="Subject"
            />

            <base-textarea
              v-model="contact.msg"
              class="mb-6"
              label="Your Advice & Comment"
            />

            <base-btn
              :color="!theme.isDark ? 'accent' : 'white'"
              outlined
              target="_blank"
              @click="sendMessage"
            >
              Send message
            </base-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  export default {
    name: 'SectionKeepInTouch',

    props: {
      subtitle: String,
      title: {
        type: String,
        default: 'SEND ME YOUR MESSAGE',
      },
    },
    data: () => ({
      contact: {
        name: '',
        email: '',
        subject: '',
        msg: '',
      },
    }),
    inject: ['theme'],
    methods: {
      sendMessage () {
        const _this = this
        if (this.contact.name !== '' && this.contact.msg !== '' &&
          this.contact.email !== '' && this.contact.subject !== '') {
          this.$axios.post('/sendMsg', this.contact).then(res => {
            this.$toast('your message has been send.')
            _this.contact.name = ''
            _this.contact.email = ''
            _this.contact.subject = ''
            _this.contact.msg = ''
          })
        } else {
          this.$toast('内容不能为空')
        }
      },
    },
  }
</script>
