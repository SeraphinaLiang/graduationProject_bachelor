<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'User',

    metaInfo: { title: 'My Page' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'person-info',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'user',
      },
    },
  }
</script>
