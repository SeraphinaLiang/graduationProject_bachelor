<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Forum',

    metaInfo: { title: 'The Forum' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'search',
        'posts-demo',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'forum',
      },
    },
  }
</script>
