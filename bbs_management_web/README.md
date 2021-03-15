
## File Structure

```
├─.browserslistrc
├─.editorconfig
├─.env
├─.eslintrc.js
├─.gitignore
├─babel.config.js
├─CHANGELOG.md
├─cypress.json
├─debug.log
├─demo.txt
├─ISSUE_TEMPLATE.md
├─jest.config.js
├─LICENSE.md
├─now.json
├─package-lock.json
├─package.json     #依赖配置
├─postcss.config.js
├─README.md
├─vue.config.js
├─tests
|   ├─unit
|   |  ├─.eslintrc.js
|   |  └example.spec.js
|   ├─e2e
|   |  ├─.eslintrc.js
|   |  ├─support
|   |  |    ├─commands.js
|   |  |    └index.js
|   |  ├─specs
|   |  |   └test.js
|   |  ├─plugins
|   |  |    └index.js
├─src
|  ├─App.vue  #入口
|  ├─axiosConfig.js  #axios全局拦截
|  ├─i18n.js  #国际化
|  ├─main.js  #全局vue配置
|  ├─router.js  #路由配置
|  ├─views  #视图
|  |   ├─dashboard  #管理界面
|  |   |     ├─Dashboard.vue   #Echart动态图表数据可视化模块
|  |   |     ├─Index.vue #基本界面结构
|  |   |     ├─tables
|  |   |     |   └RegularTables.vue #帖子管理模块
|  |   |     ├─pages #
|  |   |     |   └UserProfile.vue #用户管理模块
|  |   |     ├─components #基本组件
|  |   |     |     ├─core
|  |   |     |     |  ├─AppBar.vue #基本面板Bar
|  |   |     |     |  ├─Drawer.vue #基本面板左侧选择框
|  |   |     |     |  ├─Footer.vue #基本面板Footer
|  |   |     |     |  ├─Settings.vue #右侧固定设置
|  |   |     |     |  └View.vue  #各个模块显示
|  |   |     ├─component
|  |   |     |     ├─Notifications.vue  #用户私信管理模块
|  |   |     |     └Typography.vue  #评论管理模块
|  ├─store  # 状态管理器
|  |   └store.js
|  ├─sass  #样式
|  |  ├─overrides.sass
|  |  ├─variables.scss
|  |  ├─vuetify-material
|  |  |        ├─_appbar.sass
|  |  |        ├─_buttons.sass
|  |  |        ├─_card.sass
|  |  |        ├─_chip.sass
|  |  |        ├─_footer.sass
|  |  |        ├─_map.sass
|  |  |        ├─_modal.sass
|  |  |        ├─_notification.sass
|  |  |        ├─_pagination.sass
|  |  |        ├─_settings.sass
|  |  |        ├─_sidebar.sass
|  |  |        ├─_tab.sass
|  |  |        ├─_table.sass
|  |  |        └_view.sass
|  ├─plugins  #插件
|  |    ├─base.js  #lodash
|  |    ├─chartist.js  #图表
|  |    ├─vee-validate.js  #表单验证
|  |    └vuetify.js   #样式框架
|  ├─locales
|  |    └en.json  #i18n国际化
|  ├─components  #界面复用基本组件
|  |     ├─base
|  |     |  ├─Card.vue
|  |     |  ├─Item.vue
|  |     |  ├─ItemGroup.vue
|  |     |  ├─ItemSubGroup.vue
|  |     |  ├─MaterialAlert.vue
|  |     |  ├─MaterialCard.vue
|  |     |  ├─MaterialChartCard.vue
|  |     |  ├─MaterialSnackbar.vue
|  |     |  ├─MaterialStatsCard.vue
|  |     |  ├─MaterialTabs.vue
|  |     |  ├─MaterialTestimony.vue
|  |     |  ├─Subheading.vue
|  |     |  └VComponent.vue
|  ├─assets  #静态图片资源
|  |   ├─clint-mckoy.jpg
|  |   ├─lock.jpg
|  |   ├─login.jpg
|  |   ├─logo.png
|  |   ├─pricing.jpg
|  |   ├─register.jpg
|  |   └vuetify.svg
├─public
|   ├─favicon.ico
|   ├─favicon.png
|   └index.html  #视图注入入口
```
