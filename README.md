# pixiv-utils
Pixiv工具类

# 引入
```xml

<project>
  <repositories>
    <repository>
      <id>org.gin</id>
      <name>pixiv-utils</name>
      <url>https://agmonk.github.io/pixiv-utils/maven-repo/</url>
    </repository>
  </repositories>

  <dependencies>
    <dependency>
      <groupId>org.gin</groupId>
      <artifactId>pixiv-utils</artifactId>
      <version>1.0.0</version>
    </dependency>
  </dependencies>

</project>
```

# 使用方法

- API静态方法分组保存在`Api开头` 的类中，各类名在后续说明中标注
- `Pixiv`类的`DOMAIN`静态变量可以修改，如果你想要连接某个反代的话。
- 所有方法均需要`OkHttpClient`作为参数，请自行创建，或者可以使用`Pixiv.CLIENT`的默认Client
- 所有方法均需要`PixivCookieToken`作为参数
  - 构造方法的参数`phpSessionId`通过在WEB端登陆Pixiv之后，在`cookie`中获取，其前缀为`PHPSESSID=`
  - 构造方法的参数`token`在所有`POST`请求中使用，可以在WEB端通过F12在请求头中获取，其字段名为`x-csrf-token`，或者使用本类的`findToken`方法获取。
- 所有方法都会返回`PixivRequest`对象，该类的`sync`(同步)和`async`(异步)方法用于发送请求
  - 同步方法直接返回响应结果，可以通过传递一个`Convertor`接口将`ResponseBody`类转换为指定对象，`Convertor`
    中自带了部分接口的预设转换方法，或者使用`common`通用方法
  - 异步方法在`Callback`参数中处理异常和响应，更推荐使用参数为`BaseCallback`的重载方法；`BaseCallback`类的 `convert`方法作用与`Convertor`
    接口相同，也可以直接调用`Convertor`接口的静态方法
- 实例见：[Demo.java](https://github.com/AgMonk/pixiv-utils/blob/master/src/main/java/org/gin/Demo.java)

# Pixiv API

## 插画/漫画

类名：`ApiIllustManga`

### 查询详情

- 方法名：detail
- URL：`https://www.pixiv.net/ajax/illust/${pid}`
- 请求方法：`GET`
- 参数含义：
  - pid：作品ID、pid
- 响应结构(部分)：
  - alt： 网页标题
  - bookmarkCount： 收藏数
  - bookmarkData： 收藏数据
  - commentCount： 评论数
  - createDate： 创建时间
  - description： 作品描述
  - height： 高
  - id： pid
  - illustComment： 作品描述
  - illustId： pid
  - illustTitle： 作品标题
  - illustType： 作品类型 插画=0 漫画=1 动图=2
  - likeCount： 喜欢数量
  - pageCount： 作品含有的图片数量
  - request： 约稿信息(如果是约稿作品)
  - tags： 标签
  - title： 标题
  - uploadDate： 上传时间
  - urls： 图片url
  - userAccount： 用户账号
  - userId： 用户id
  - userName： 用户昵称
  - viewCount： 浏览次数
  - width： 宽
- 提示：对于动图，原图地址需要把`img-original`替换为`img-zip-ugoira`，把结尾的`_ugoira0.jpg`替换为`_ugoira1920x1080.zip`，即只能下载压缩包，需要把里面的图片自行合成为GIF。

### 搜索

- 方法名：search
- URL：`https://www.pixiv.net/ajax/search/artworks/${keyword}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - keyword：(PATH传递)关键字，需使用UTF-8编码，编码前的空格(编码后的+号)需要替换为`%20`
  - order：排序模式，可选值：`date_d`从新到旧(默认),`date`从旧到新
  - mode：模式，可选值：`all`、`safe`、`r18`
  - lang：语言，简中为 `zh`
  - p：页码
  - scd：发布时间（起），以东九区为准，格式：yyyy-MM-dd
  - ecd：发布时间（止），以东九区为准，格式：yyyy-MM-dd

### 查询作品的收藏状态

- 方法名：bookmarkData
- URL：`https://www.pixiv.net/ajax/illust/${pid}/bookmarkData`
- 请求方法：`GET`
- 传参方式：`PATH`
- 参数含义：
  - pid：作品id

### 查询动图的其他信息

- 方法名：ugoiraMeta
- URL：`https://www.pixiv.net/ajax/illust/${pid}/ugoira_meta`
- 请求方法：`GET`
- 传参方式：`PATH`
- 参数含义：
  - pid：动图作品id

## 收藏

类名：`ApiBookmark`

### 收藏漫画/插画

- 方法名：addIllust

- URL：`https://www.pixiv.net/ajax/illusts/bookmarks/add`

- 请求方法：`POST`

- 传参方式：`BODY`

- 参数结构：

  ```json
  {
      "illust_id": 1111,
      "restrict": 0,
      "comment": "",
      "tags": []
  }
  ```

- 参数含义：

  - illust_id：作品ID、pid
  - restrict：是否为公开收藏。0 = 公开，1 = 非公开
  - comment：备注
  - tags：标签

- 响应：成功时将会返回收藏id

### 删除收藏漫画/插画

- 方法名：delIllust
- URL：`https://www.pixiv.net/ajax/illusts/bookmarks/delete`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：

  - bookmark_id：收藏id，从作品信息的bookmarkData字段中获取

### 删除收藏漫画/插画(批量)

- 方法名：delIllusts

- URL：`https://www.pixiv.net/ajax/illusts/bookmarks/remove `

- 请求方法：`POST`

- 传参方式：`BODY`

- 参数结构：

  ```json
  {
      "bookmarkIds": []
  }
  ```

- 参数含义：

  - bookmarkIds：收藏id，从作品信息的bookmarkData字段中获取

### 收藏小说

- 方法名：addNovel

- URL：`https://www.pixiv.net/ajax/novels/bookmarks/add`

- 请求方法：`POST`

- 传参方式：`BODY`

- 参数结构：

  ```json
  {
      "novel_id": 1111,
      "restrict": 0,
      "comment": "",
      "tags": []
  }
  ```

- 参数含义：

  - novel_id：小说id
  - restrict：是否为公开收藏。0 = 公开，1 = 非公开
  - comment：备注
  - tags：标签

- 响应：成功时将会返回收藏id

### 删除收藏小说

- 方法名：delNovel
- URL：`https://www.pixiv.net/ajax/novels/bookmarks/delete`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：

  - book_id：收藏id，从作品信息的bookmarkData字段中获取
  - del：固定为1

### 删除收藏小说(批量)

- 方法名：delNovels

- URL：`https://www.pixiv.net/ajax/novels/bookmarks/remove `

- 请求方法：`POST`

- 传参方式：`BODY`

- 参数结构：

  ```json
  {
      "bookmarkIds": []
  }
  ```

- 参数含义：

  - bookmarkIds：收藏id，从作品信息的bookmarkData字段中获取

## 关注

类名：`ApiFollows`

### 查询关注作者的最新插画/漫画

- 方法名：latestIllust
- URL：`https://www.pixiv.net/ajax/follow_latest/illust `
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
    - mode：模式，可选值：`all`,`r18`
    - lang：语言，简中为 `zh`
    - p：页码

### 查询关注作者的最新小说

- 方法名：latestNovel
- URL：`https://www.pixiv.net/ajax/follow_latest/novel `
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
    - mode：模式，可选值：`all`,`r18`
    - lang：语言，简中为 `zh`
    - p：页码

### 关注作者

- 方法名：add
- URL：`https://www.pixiv.net/bookmark_add.php`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：

  - mode：固定为`add`
  - type：固定为`user`
  - user_id：需要关注的用户id
  - tag：标签，可以留空
  - restrict：是否公开。0 = 公开，1 = 非公开
  - format：固定为`json`

### 取关作者

- 方法名：del
- URL：`https://www.pixiv.net/rpc_group_setting.php`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：

  - mode：固定为`del`
  - type：固定为`bookuser`
  - id：需要取关的用户id

## 用户

类名：`ApiUser`

### 查询用户信息

- 方法名：userInfo
- URL：`https://www.pixiv.net/ajax/user/${uid}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - full：固定为`1`，传递该参数将获得额外信息
  - lang：语言，简中为 `zh`

### 作品

#### 查询用户的作品概况

- 方法名：profileAll
- URL：`https://www.pixiv.net/ajax/user/${uid}/profile/all`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - lang：语言，简中为 `zh`
- 响应：这里会返回所有作品的id，但是没有作品信息，需要使用后续接口来获取

#### 查询用户的插画/漫画

- 方法名：profileIllusts
- URL：`https://www.pixiv.net/ajax/user/${uid}/profile/illusts`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - lang：语言，简中为 `zh`
  - is_first_page：是否为第一页。1 = 是 ，0 = 否。返回结果疑似没有区别
  - work_category： 作品类型，可选值：`illust`(插画)、`manga`(漫画)、`illustManga`(二者混合)。疑似不敏感，但是不能省略
  - ids[]：需要查询的作品id，可以传多个

#### 查询用户发出约稿的作品

- 方法名：commissionRequestSent
- URL：`https://www.pixiv.net/ajax/commission/page/users/${uid}/request/sent`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - lang：语言，简中为 `zh`

#### 查询用户的小说

- 方法名：profileNovels
- URL：`https://www.pixiv.net/ajax/user/${uid}/profile/novels`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - lang：语言，简中为 `zh`

### 收藏

#### 查询用户收藏的插画/漫画

- 方法名：illustsBookmarks
- URL：`https://www.pixiv.net/ajax/user/${uid}/illusts/bookmarks`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - lang：语言，简中为 `zh`
  - tag：带有的标签
  - offset： 跳过前面 x 个查询结果
  - limit：每次查询返回的结果条数上限，最大为100
  - rest：`show`公开的，`hide`不公开的(仅自己)
- 备注：tag参数写`未分類`可以查询到添加收藏时未提供标签(网页端/APP的默认操作)的作品

#### 查询用户的收藏的插画/漫画中使用的标签

- 方法名：illustsBookmarkTags
- URL：`https://www.pixiv.net/ajax/user/${uid}/illusts/bookmark/tags`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - lang：语言，简中为 `zh`

#### 查询用户的收藏小说中使用的标签

- 方法名：illustsBookmarkTags
- URL：`https://www.pixiv.net/ajax/user/${uid}/novels/bookmark/tags`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - lang：语言，简中为 `zh`

## 小说

类名：`ApiNovel`

### 搜索

- 方法名：search
- URL：`https://www.pixiv.net/ajax/search/novels/${keywords}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - keywords：(PATH传递)关键字，需使用UTF-8编码，编码前的空格(编码后的+号)需要替换为`%20`
  - order：排序模式，可选值：`date_d`从新到旧(默认),`date`从旧到新
  - mode：模式，可选值：`all`、`safe`、`r18`
  - s_mode：检索范围，`s_tag`(默认),`s_tag_only`(标签、部分一致),`s_tag_full`(标签，完全一致),`s_tc`(正文)
  - lang：语言，简中为 `zh`
  - p：页码
  - scd：发布时间（起），以东九区为准，格式：yyyy-MM-dd
  - ecd：发布时间（止），以东九区为准，格式：yyyy-MM-dd
  - tlt：字数范围（最低）
  - tgt：字数范围（最高），官方提供的字数范围选项为：`0-4999`,`5000-19999`,`20000-79999`,`80000-`，指定文字数为会员功能
  - gs：是否以系列分组显示

### 详情

- 方法名：detail
- URL：`https://www.pixiv.net/ajax/novel/${nid}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - nid：(PATH传递)小说的id
  - lang：语言，简中为 `zh`

### 查询系列

- 方法名：series
- URL：`https://www.pixiv.net/ajax/novel/series/${seriesId}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - seriesId：(PATH传递)小说系列的id
  - lang：语言，简中为 `zh`

## 评论区

类名：`ApiComments`

### 查询作品评论（根）

- 方法名：illustsRoots
- URL：`https://www.pixiv.net/ajax/illusts/comments/roots`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - illust_id：作品pid
  - offset：
  - limit：
  - lang：语言，简中为 `zh`

### 查询作品回复（楼中楼）

- 方法名：illustsReplies
- URL：`https://www.pixiv.net/ajax/illusts/comments/replies`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - comment_id：评论id
  - page：
  - lang：语言，简中为 `zh`

### 发布评论

- 方法名：comment
- URL：`https://www.pixiv.net/rpc/post_comment.php`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：
  - type：只能选择`comment`(文字评论)或`stamp`(表情贴图)
  - illust_id：作品id
  - author_user_id：作者uid
  - parent_id：如果发楼中楼评论，需要提供父楼id
  - comment：如果type选择comment，此字段为文字内容
  - stampId：如果type选择Stamp，此字段为贴图的id

### 删除评论

- 方法名：delComment
- URL：`https://www.pixiv.net/rpc_delete_comment.php`

- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：
  - i_id：作品id
  - del_id：评论id