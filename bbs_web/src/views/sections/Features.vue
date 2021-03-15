<template>
  <div
    id="features"
    style="background-color: #ffa000 ;text-align: center;"
  >
    <v-container fluid>
      <v-container>
        <h3 style="color: white">
          部分管理界面展示 Management Interface Demonstration
        </h3>
      </v-container>
      <v-container>
        <h4 style="color: white">
          数据可视化 Echarts Data Visualization
        </h4>
      </v-container>
      <row>
        <col>
        <img
          :src="require(`@/assets/managementPage/table1.jpg`)"
          width="550px"
        >
        <col>
        <img
          :src="require(`@/assets/managementPage/table2.jpg`)"
          width="665px"
        >
      </row>
      <v-container>
        <h4 style="color: white">
          后台发帖管理 Post Management
        </h4>
      </v-container>
      <row>
        <img
          :src="require(`@/assets/managementPage/table3.jpg`)"
          width="1215px"
        >
      </row>
      <v-container>
        <h4 style="color: white">
          后台用户管理 User Management
        </h4>
      </v-container>
      <row>
        <img
          :src="require(`@/assets/managementPage/table4.jpg`)"
          width="1215px"
        >
      </row>
    </v-container>
  </div>
</template>

<script>
  export default {
    name: 'SectionFeatures',

    data: () => ({
    }),
  }
</script>
