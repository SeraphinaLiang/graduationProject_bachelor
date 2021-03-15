# 后端

data放置在resources文件夹下 https://github.com/hankcs/HanLP/tree/1.x

## project structure

```
├─src
|  ├─test
|  |  ├─java
|  |  |  ├─com
|  |  |  |  ├─example
|  |  |  |  |    ├─bbs
|  |  |  |  |    |  └BbsApplicationTests.java  测试类
|  ├─main
|  |  ├─resources
|  |  |     ├─application.properties  #spring,mybatis,server配置信息
|  |  |     ├─application.yml         #登录jwt配置信息
|  |  |     ├─hanlp.properties        #hanlp自然语言处理工具配置信息
|  |  |     ├─static
|  |  |     |   ├─banner.jpg           #启动图片
|  |  |     |   └SensitiveWords.txt    #敏感词列表
|  |  |     ├─META-INF
|  |  |     |    └spring-devtools.properties
|  |  |     ├─mapper
|  |  |     |   ├─user
|  |  |     |   |  └UserMapper.xml   #用户
|  |  |     |   ├─tag
|  |  |     |   |  └TagMapper.xml    #标签
|  |  |     |   ├─star
|  |  |     |   |  └StarMapper.xml   #标星
|  |  |     |   ├─post
|  |  |     |   |  └PostMapper.xml   #帖子
|  |  |     |   ├─module
|  |  |     |   |   └ModuleMapper.xml   #模块
|  |  |     |   ├─message
|  |  |     |   |    └messageMapper.xml   #私信
|  |  |     |   ├─comment
|  |  |     |   |    └CommentMapper.xml  #评论
|  |  |     ├─data       # hanlp语言和模型包
|  |  |     |  ├─model
|  |  |     |  ├─dictionary
|  |  ├─java
|  |  |  ├─com
|  |  |  |  ├─example
|  |  |  |  |    ├─bbs
|  |  |  |  |    |  ├─BBSApplication.java  #主类
|  |  |  |  |    |  ├─util   #工具包
|  |  |  |  |    |  |  ├─JwtUtils.java    #jwt登录生成token
|  |  |  |  |    |  |  ├─ResourceFileIoAdapter.java   #更改hanlp配置读取resources下的data包
|  |  |  |  |    |  |  ├─nlp  #基于hanlp api网络接口实现语言处理
|  |  |  |  |    |  |  |  ├─Crf.java  #CRF分词
|  |  |  |  |    |  |  |  ├─KeyWordExtract.java  #关键字提取
|  |  |  |  |    |  |  |  ├─TextSuggest.java  #文本推荐
|  |  |  |  |    |  |  |  ├─token.java  #接口的token
|  |  |  |  |    |  |  |  ├─dataType  #发送和返回数据处理工具包
|  |  |  |  |    |  |  |  |    ├─ExtractWord.java  #整合crf和关键词提取，得到分词结果
|  |  |  |  |    |  |  |  |    └SentenceArray.java  #发送数据预处理
|  |  |  |  |    |  |  ├─hanlp  #基于本地的hanlp的语言处理包
|  |  |  |  |    |  |  |   ├─TextSuggest.java  #文本推荐
|  |  |  |  |    |  |  |   └WordSegment.java  #整合CRF分词和关键字识别，得到分词结果
|  |  |  |  |    |  ├─shiro  #登录
|  |  |  |  |    |  |   ├─AccountProfile.java  #登录返回的用户信息
|  |  |  |  |    |  |   ├─AccountRealm.java  #获取登录信息
|  |  |  |  |    |  |   ├─JwtFilter.java  #登录处理，请求拦截
|  |  |  |  |    |  |   └JwtToken.java  #token类
|  |  |  |  |    |  ├─service
|  |  |  |  |    |  |    ├─functional  #根据功能分类的服务
|  |  |  |  |    |  |    |     ├─MailService.java  #邮件发送
|  |  |  |  |    |  |    |     ├─SensitiveService.java  #基于前缀树算法的敏感词汇识别和过滤
|  |  |  |  |    |  |    |     ├─UserEditService.java  #用户主页编辑接口
|  |  |  |  |    |  |    |     └UserEditServiceImpl.java   #用户主页编辑接口实现类
|  |  |  |  |    |  |    ├─basic  #基础服务接口
|  |  |  |  |    |  |    |   ├─CommentService.java
|  |  |  |  |    |  |    |   ├─MessageService.java
|  |  |  |  |    |  |    |   ├─ModuleService.java
|  |  |  |  |    |  |    |   ├─PostService.java
|  |  |  |  |    |  |    |   ├─StarService.java
|  |  |  |  |    |  |    |   ├─TagService.java
|  |  |  |  |    |  |    |   ├─UserService.java
|  |  |  |  |    |  |    |   ├─implement   #接口实现包
|  |  |  |  |    |  |    |   |     ├─CommentServiceImpl.java
|  |  |  |  |    |  |    |   |     ├─ModuleServiceImpl.java
|  |  |  |  |    |  |    |   |     ├─PostServiceImpl.java
|  |  |  |  |    |  |    |   |     ├─StarServiceImpl.java
|  |  |  |  |    |  |    |   |     ├─TagServiceImpl.java
|  |  |  |  |    |  |    |   |     └UserServiceImpl.java
|  |  |  |  |    |  ├─mapper   #dao层
|  |  |  |  |    |  |   ├─user
|  |  |  |  |    |  |   |  ├─UserDao.java
|  |  |  |  |    |  |   |  ├─bean
|  |  |  |  |    |  |   |  |  ├─UserEntity.java   #用户实体类
|  |  |  |  |    |  |   |  |  ├─dto    #数据传输对象
|  |  |  |  |    |  |   |  |  |  ├─UserLoginDTO.java  #用户登录
|  |  |  |  |    |  |   |  |  |  ├─UserModifiedDTO.java  #用户信息修改
|  |  |  |  |    |  |   |  |  |  ├─UserPasswordEditDTO.java   #密码修改
|  |  |  |  |    |  |   |  |  |  └UserRegisterDTO.java  #注册
|  |  |  |  |    |  |   ├─tag
|  |  |  |  |    |  |   |  ├─TagDao.java
|  |  |  |  |    |  |   |  ├─bean
|  |  |  |  |    |  |   |  |  ├─TagEntity.java  #帖子实体类
|  |  |  |  |    |  |   |  |  └TagHot.java  #热门帖子拉取
|  |  |  |  |    |  |   ├─star
|  |  |  |  |    |  |   |  ├─StarDao.java
|  |  |  |  |    |  |   |  ├─bean
|  |  |  |  |    |  |   |  |  └StarEntity.java  #标星实体类
|  |  |  |  |    |  |   ├─post
|  |  |  |  |    |  |   |  ├─PostDao.java
|  |  |  |  |    |  |   |  ├─bean
|  |  |  |  |    |  |   |  |  ├─PostEntity.java  #帖子实体类
|  |  |  |  |    |  |   |  |  ├─dto
|  |  |  |  |    |  |   ├─module
|  |  |  |  |    |  |   |   ├─ModuleDao.java
|  |  |  |  |    |  |   |   ├─bean
|  |  |  |  |    |  |   |   |  └ModuleEntity.java  #模块实体类
|  |  |  |  |    |  |   ├─message
|  |  |  |  |    |  |   |    ├─MessageDao.java
|  |  |  |  |    |  |   |    └MessageEntity.java  #私信实体类
|  |  |  |  |    |  |   ├─comment
|  |  |  |  |    |  |   |    ├─CommentDao.java
|  |  |  |  |    |  |   |    ├─bean
|  |  |  |  |    |  |   |    |  ├─AddCommentDTO.java  #增加评论DTO
|  |  |  |  |    |  |   |    |  └CommentEntity.java  #评论实体类
|  |  |  |  |    |  ├─handler
|  |  |  |  |    |  |    └MyMetaObjectHandler.java  #mybatis自动生成insert/update时间戳
|  |  |  |  |    |  ├─controller
|  |  |  |  |    |  |     ├─funtional
|  |  |  |  |    |  |     |     ├─LoginLogoutController.java  #登录退出
|  |  |  |  |    |  |     |     ├─MailController.java  #注册验证码邮件发送
|  |  |  |  |    |  |     |     ├─ManagementController.java  #管理界面
|  |  |  |  |    |  |     |     ├─SearchController.java  #搜索
|  |  |  |  |    |  |     |     └UserEditController.java  #用户编辑
|  |  |  |  |    |  |     ├─basic
|  |  |  |  |    |  |     |   ├─CommentController.java
|  |  |  |  |    |  |     |   ├─ModuleController.java
|  |  |  |  |    |  |     |   ├─PostController.java
|  |  |  |  |    |  |     |   ├─StarController.java
|  |  |  |  |    |  |     |   ├─TagController.java
|  |  |  |  |    |  |     |   └UserController.java
|  |  |  |  |    |  ├─config
|  |  |  |  |    |  |   ├─CorsConfig.java  #跨域处理、全局拦截配置
|  |  |  |  |    |  |   ├─MybatisPlusConfig.java  #分页配置
|  |  |  |  |    |  |   ├─ShiroConfig.java  #登录配置
|  |  |  |  |    |  |   └SwaggerConfig.java  #swagger-ui配置
|  |  |  |  |    |  ├─common
|  |  |  |  |    |  |   ├─exception
|  |  |  |  |    |  |   |     └GlobalExceptionHandler.java  #全局异常处理
|  |  |  |  |    |  |   ├─domain
|  |  |  |  |    |  |   |   ├─ResponseDTO.java   #返回消息包装类
|  |  |  |  |    |  |   |   └Result.java  #登录返回消息
|  |  |  |  |    |  |   ├─constant
|  |  |  |  |    |  |   |    └ResponseCodeConst.java  #返回消息类
```
