<template>
  <v-theme-provider dark>
    <base-section
      id="social"
      class="accent text-center"
      space="56"
    >
      <base-section-heading
        color="transparent"
        title="数据库设计 Database Design"
      >
        数据库中主要表的主键采取了UUID的设计，同时部分表增加了乐观锁(version)和逻辑删除(deleted)。
        Adopted the design of UUID as the primary key for the main tables in the database,
        and added optimistic lock (version) and logical deletion (deleted) for some tables.
      </base-section-heading>
      <img
        :src="require(`@/assets/managementPage/sql.jpg`)"
        width="1000px"
      >
    </base-section>
  </v-theme-provider>
</template>

<script>
  export default {
    name: 'SectionNews',

    provide: {
      heading: { align: 'center' },
    },
  }
</script>
