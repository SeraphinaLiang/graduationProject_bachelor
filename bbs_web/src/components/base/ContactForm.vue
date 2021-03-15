<template>
  <div>
    <base-info-card
      :title="title"
      :subtitle="subtitle"
      space="4"
      color="primary"
    />

    <base-text-field
      v-model="contact.name"
      label="Name"
    />

    <base-text-field
      v-model="contact.email"
      label="Email"
    />

    <base-text-field
      v-model="contact.subject"
      label="Subject"
    />

    <base-textarea
      v-model="contact.description"
      class="mb-6"
      label="Your Need & Description"
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
</template>

<script>
  export default {
    name: 'BaseContactForm',

    props: {
      subtitle: String,
      title: {
        type: String,
        default: 'MAIL US YOUR MESSAGE',
      },
    },
    data: () => ({
      contact: {
        name: '',
        email: '',
        subject: '',
        description: '',
      },
    }),
    // Injected from the Vuetify Themeable mixin
    inject: ['theme'],
    methods: {
      sendMessage () {
        this.$toast('your query has been send.')
      },
    },
  }
</script>
