<template>
  <base-section id="theme-features">
    <base-section-heading title="使用的技术 Technologies Used">
      本项目分为三个子项目<br>The project is divided into three sub-projects<br>
      分别为 Service (项目服务端), BBS Web (论坛网站界面) 和 Management Web (后台管理界面)<br>
      They are Service, BBS Web (user interface) and Management Web (management interface)<br>
      以下介绍本项目所使用的技术<br>
      The technology used in these sub-projects is shown below.
    </base-section-heading>

    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in features"
          :key="i"
          cols="6"
          md="3"
        >
          <base-avatar-card
            v-bind="feature"
            align="left"
            horizontal
          />
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  export default {
    name: 'SectionThemeFeatures',

    data: () => ({
      features: [
        {
          title: 'SpringBoot',
          text: '基于SpringBoot框架的Java Web应用 & A Java Web Application based on SpringBoot Framework',
        },
        {
          title: 'MyBatis-Plus',
          text: '持久层框架-使用了MyBatis的增强工具包, 增加乐观锁和逻辑删除, 实现分页和CURD等基本操作 ' +
            '& Persistence layer framework - used a powerful enhanced tool for MyBatis',
        },
        {
          title: 'MySQL',
          text: '基于MySQL数据库 & Based on MySQL database',
        },
        {
          title: 'Shiro & jwt',
          text: '使用了Apache Shiro的安全框架和Jwt token认证，实现了登录退出，验证用户身份和访问控制功能 & ' +
            'Apply Apache Shiro Security Framework and Json Web Token authentication to implement Login&Logout, ' +
            'User Identity Verification and ' +
            'Access Control',
        },
        {
          title: 'Hanlp自然语言处理',
          text: '基于Hanlp自然语言处理工具包，采用了CRF分词，TextRank关键词提取，动态调节权重的文本推荐，以优化搜索功能 & ' +
            'Based on Hanlp NLP Utility Package, adopted CRF Word Segmentation, TextRank Keyword' +
            ' Extraction and Dynamic Weight Adjusted Text Suggest, to optimize searching experience',
        },
        {
          title: 'Trie 前缀树算法',
          text: '基于前缀树算法实现敏感词汇的识别和过滤 & Sensitive words identification and filtration based on Trie Algorithm ',
        },
        {
          title: 'MD5 单向加密',
          text: '采用了MD5单向加密技术对用户的密码进行加密 & Adopted Message-Digest Algorithm 5, an one-way encryption technology,' +
            ' to encrypt users\' password',
        },
        {
          title: 'Others 服务端其他技术',
          text: 'SpringBoot Mail 邮箱验证码发送' +
            'Thumbnailator 生成图片缩略图   ' +
            'Swagger-UI & Postman 后端测试           ',
        },
        {
          title: 'VUE',
          text: '视图层使用了Vue.js,一款轻量级JavaScript技术开发框架, 采取了组件复用的设计以简化代码,实现了数据的双向绑定 ' +
            '& The view layer uses Vue.js, a lightweight JavaScript development framework,' +
            ' adopts the design of component reuse to simplify the code and' +
            ' implements two-way bindings of data',
        },
        {
          title: 'Vuetify & sass',
          text: '使用了Vuetify Material Design样式框架进行视图的渲染，' +
            '和Sass层叠样式表语言进行组件和界面样式的调节 & ' +
            'Used Vuetify Material Design style framework to render the view,' +
            ' and Sass cascading style sheet language to adjust the components and interfaces styles',
        },
        {
          title: 'Echarts',
          text: '基于Apache Echarts实现了数据可视化，管理界面实时抽取后台数据，生成静态图表和随着时间变化的动态图表，' +
            '以用于进行数据分析和数据统计 & ' +
            'Data visualization is realized based on Apache Echarts. ' +
            'The management interface has real-time access to background data' +
            ' which generates static tables and dynamic charts that change over time ' +
            'for data analysis and statistics purposes.',
        },
        {
          title: 'vue router',
          text: '使用Vue.js的路由插件，基于路由和组件实现页面切换和跳转，增加路由权限拦截，实现登录判断访问控制 & ' +
            'Use Vue.js router plugin to realize page switching and jumping based on routing and components,' +
            ' adding router permission interception to realize login access control',
        },
        {
          title: 'vuex',
          text: '采用了vuex状态管理模式来管理组件间的共享数据，同时模块化管理登录信息，方便处理后续http请求 ' +
            '& The vuex state management mode is adopted to manage the shared data between components, ' +
            'and the login information is modularized to manage subsequent http requests.',
        },
        {
          title: 'vee-validate',
          text: '使用了轻量级的vue表单校验插件vee-validate, 采用基于模板的规则验证用户输入的合法性，简化后端代码设计 & ' +
            'Use the lightweight form verification plugin vee-validate, adopt  template-based rules ' +
            'to verify the validity of user input to simplify the back-end code design',
        },
        {
          title: 'quill-editor',
          text: '使用轻量级富文本编辑器Vue-Quill-Editor，实现帖子的发布和显示 & ' +
            'Use Vue-Quill-Editor, a lightweight rich text editor to handle publishing and viewing posts',
        },
        {
          title: 'axios',
          text: '使用axios来完成ajax请求，定义了全局axios拦截器，统一处理返回结果 & ' +
            'Use axios to complete ajax requests, define a global axios interceptor ' +
            'to process returned responses globally',
        },
      ],
    }),
  }
</script>
