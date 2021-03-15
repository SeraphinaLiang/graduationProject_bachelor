<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Offer',

    metaInfo: { title: 'Share Offers' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'search',
        'offers-demo',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'offer',
      },
    },
  }
</script>
