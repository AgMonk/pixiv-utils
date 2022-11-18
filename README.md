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

1. 创建`PixivApi`对象，方法见[Demo.java](https://github.com/AgMonk/pixiv-utils/blob/master/src/main/java/org/gin/Demo.java)

2. 参数`sessionId`通过在WEB端登陆Pixiv之后，在`cookie`中获取，其前缀为`PHPSESSID=`

3. `PixivApi`对象的`xxxApi`字段为各用途的API分组

   | 字段名           | 用途            |      |
   | ---------------- | --------------- | ---- |
   | illustApi        | 绘画作品API     |      |
   | novelApi         | 小说API         |      |
   | novelSeriesApi   | 小说系列API     |      |
   | userApi          | 用户API         |      |
   | bookmarkApi      | 收藏相关API     |      |
   | commentIllustApi | 绘画作品评论API |      |
   | commentNovelApi  | 小说评论API     |      |
   | rankingApi       | 排行榜API       |      |
   | userWorksApi     | 用户作品API     |      |
   | userBookmarkApi  | 用户收藏API     |      |
   | tagApi           | 标签API         |      |

4. 所有API方法都会返回`PixivRequest`对象，该类的如下4个方法均可以发送请求
  1. `async(PixivCallback<R> pixivCallback)`：(推荐)异步请求，可以直接在接口中拿到返回对象
2. ` R sync()`：(推荐)同步请求，可以直接从返回中拿到对象，需要自行处理抛出的异常
3. `async(Callback callback)`： 异步请求，OKHttp的原生方法，需要自行解析Body，仅作备用；
4. `syncBody()`：同步请求，OKHttp的原生方法，需要自行解析Body和处理抛出的异常，仅作备用；

# Pixiv API

所有接口都接收 query 参数 `lang`，指定你所使用的语言，会影响标签翻译，和报错信息等使用的语言。

## 绘画作品API - illustApi

### 查询收藏状态

- 方法名：bookmarkData
- URL：`https://www.pixiv.net/ajax/illust/${pid}/bookmarkData`
- 请求方法：`GET`
- 传参方式：`PATH`
- 参数含义：
  - pid：作品id

### 查询详情

- 方法名：detail
- URL：`https://www.pixiv.net/ajax/illust/${pid}`
- 请求方法：`GET`
- 参数含义：
  - pid：作品ID、pid
- 提示：对于动图，原图地址需要把`img-original`替换为`img-zip-ugoira`，把结尾的`_ugoira0.jpg`替换为`_ugoira1920x1080.zip`，即只能下载压缩包，需要把里面的图片自行合成为GIF。

### 发现

- 方法名：discovery
- URL：`https://www.pixiv.net/ajax/discovery/artworks`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - mode：模式，可选值：`all`、`safe`、`r18`
  - limit：一次返回的作品数量
  - sampleIllustId: （非必填）参考作品id

### 查询关注作者的最新绘画

- 方法名：latest
- URL：`https://www.pixiv.net/ajax/follow_latest/illust `
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - mode：模式，可选值：`all`,`r18`
  - p：页码

### 喜欢绘画

- 方法名：like
- URL：`https://www.pixiv.net/ajax/illusts/like`
- 请求方法：`POST`
- 传参方式：`BODY`
- 参数含义：

  - illust_id：作品ID、pid

### 查询推荐作品

- 方法名：recommendInit
- URL：`https://www.pixiv.net/ajax/illust/${pid}/recommend/init`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - pid(PATH)：基准作品id
  - limit：作品数量

### 查询推荐作品2

- 方法名：recommendIllusts
- URL：`https://www.pixiv.net/ajax/illust/recommend/illusts`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - illust_ids[]：数组，基准作品id，可以使用前一个接口的返回结果

### 搜索

- 方法名：search
- URL：`https://www.pixiv.net/ajax/search/artworks/${keyword}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - keyword：(PATH传递)关键字，需使用UTF-8编码，编码前的空格(编码后的+号)需要替换为`%20`
  - order：排序模式，可选值：`date_d`从新到旧(默认),`date`从旧到新
  - mode：模式，可选值：`all`、`safe`、`r18`
  - p：页码
  - scd：发布时间（起），以东九区为准，格式：yyyy-MM-dd
  - ecd：发布时间（止），以东九区为准，格式：yyyy-MM-dd

### 查询动图的其他信息

- 方法名：ugoiraMeta
- URL：`https://www.pixiv.net/ajax/illust/${pid}/ugoira_meta`
- 请求方法：`GET`
- 传参方式：`PATH`
- 参数含义：
  - pid：动图作品id

## 小说API - novelApi

### 查询收藏状态

- 方法名：bookmarkData
- URL：`https://www.pixiv.net/ajax/novel/${nid}/bookmarkData`
- 请求方法：`GET`
- 传参方式：`PATH`
- 参数含义：
  - nid：小说id

### 详情

- 方法名：detail
- URL：`https://www.pixiv.net/ajax/novel/${nid}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - nid：(PATH传递)小说的id

### 发现

- 方法名：discovery
- URL：`https://www.pixiv.net/ajax/discovery/novels`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - mode：模式，可选值：`all`、`safe`、`r18`
  - limit
  - sampleNovelId: （非必填）参考小说id

### 查询关注作者的最新小说

- 方法名：latestNovel
- URL：`https://www.pixiv.net/ajax/follow_latest/novel `
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - mode：模式，可选值：`all`,`r18`
  - p：页码

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
  - p：页码
  - scd：发布时间（起），以东九区为准，格式：yyyy-MM-dd
  - ecd：发布时间（止），以东九区为准，格式：yyyy-MM-dd
  - tlt：字数范围（最低）
  - tgt：字数范围（最高），官方提供的字数范围选项为：`0-4999`,`5000-19999`,`20000-79999`,`80000-`，指定文字数为会员功能
  - gs：是否以系列分组显示
  - work_lang：写作语言，部分语言：简中`zh-cn`，繁中`zh-tw`，英语`en`，日语`ja`

## 小说系列API - novelSeriesApi

### 查询系列

- 方法名：info
- URL：`https://www.pixiv.net/ajax/novel/series/${seriesId}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - seriesId：(PATH传递)小说系列的id

### 查询系列的各篇标题

- 方法名：titles
- URL：`https://www.pixiv.net/ajax/novel/series/${seriesId}/content_titles`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - seriesId：(PATH传递)小说系列的id

### 查询系列中作品的基础信息

- 方法名：contents
- URL：`https://www.pixiv.net/ajax/novel/series_content/${seriesId}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - seriesId：(PATH传递)小说系列的id
  - limit：
  - last_order：offset
  - order_by：排序，`dsc`或`asc`

## 用户API - userApi

### 关注

- 方法名：follow
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

### 取关

- 方法名：unfollow
- URL：`https://www.pixiv.net/rpc_group_setting.php`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：

  - mode：固定为`del`
  - type：固定为`bookuser`
  - id：需要取关的用户id

### 推荐用户

- 方法名：recommend
- URL：`https://www.pixiv.net/ajax/user/${uid}/recommends`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - userNum：推荐的用户数
  - workNum：每个用户附带的作品数量
  - isR18：Boolean ， 是否包含R18

### 查询用户信息

- 方法名：userInfo
- URL：`https://www.pixiv.net/ajax/user/${uid}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - full：固定为`1`，传递该参数将获得额外信息

## 收藏相关API - bookmarkApi

### 收藏绘画

- 方法名：addIllust

- URL：`https://www.pixiv.net/ajax/illusts/bookmarks/add`

- 请求方法：`POST`

- 传参方式：`BODY`

- 参数结构：

  ```json
  {
      "illust_id": 
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

### 删除收藏绘画

- 方法名：delIllust
- URL：`https://www.pixiv.net/ajax/illusts/bookmarks/delete`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：

  - bookmark_id：收藏id，从作品信息的bookmarkData字段中获取

### 删除收藏绘画(批量)

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

## 绘画作品评论API - commentIllustApi

### 查询绘画评论（根）

- 方法名：roots
- URL：`https://www.pixiv.net/ajax/illusts/comments/roots`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - illust_id：作品pid
  - offset：
  - limit：

### 查询绘画回复（楼中楼）

- 方法名：replies
- URL：`https://www.pixiv.net/ajax/illusts/comments/replies`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - comment_id：评论id
  - page：

### 发布评论

- 方法名：post
- URL：`https://www.pixiv.net/rpc/post_comment.php`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：
  - type：只能选择`comment`(文字评论)或`stamp`(表情贴图)
  - illust_id：作品id
  - author_user_id：作者uid
  - parent_id：如果发楼中楼评论，需要提供父楼id
  - comment：如果type选择comment，此字段为文字内容
  - stamp_id：如果type选择Stamp，此字段为贴图的id

### 删除评论

- 方法名：del
- URL：`https://www.pixiv.net/rpc_delete_comment.php`

- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：
  - i_id：作品id
  - del_id：评论id

## 小说评论API - commentNovelApi

### 查询小说评论（根）

- 方法名：novelsRoots
- URL：`https://www.pixiv.net/ajax/novels/comments/roots`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - novel_id：小说id
  - offset：
  - limit：

### 查询小说回复（楼中楼）

- 方法名：novelsReplies
- URL：`https://www.pixiv.net/ajax/novels/comments/replies`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - comment_id：评论id
  - page：

### 发布评论

- 方法名：post
- URL：`https://www.pixiv.net/novel/rpc/post_comment.php`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：
  - type：只能选择`comment`(文字评论)或`stamp`(表情贴图)
  - illust_id：作品id
  - author_user_id：作者uid
  - parent_id：如果发楼中楼评论，需要提供父楼id
  - comment：如果type选择comment，此字段为文字内容
  - stamp_id：如果type选择Stamp，此字段为贴图的id

### 删除评论

- 方法名：del
- URL：`https://www.pixiv.net/novel/rpc_delete_comment.php`

- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：
  - i_id：作品id
  - del_id：评论id

## 标签API - tagApi

### 为绘画追加标签

- 方法名：illustAdd
- URL：`https://www.pixiv.net/ajax/tags/illust/${pid}/add`
- 请求方法：`POST`
- 传参方式：`BODY`
- 参数含义：
  - pid(PATH)：作品id
  - tag：标签名

### 查询标签信息

- 方法名：tagInfo
- URL：`https://www.pixiv.net/ajax/tag/info`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - tag：标签名

### 查询标签建议(追加标签时使用)

- 方法名：suggestByWord
- URL：`https://www.pixiv.net/ajax/tags/suggest_by_word`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - word：关键字
  - content_types_to_count[]：固定为 `illust`

## 用户作品API - userWorksApi

### 查询用户的作品概况

- 方法名：all
- URL：`https://www.pixiv.net/ajax/user/${uid}/profile/all`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
- 响应：这里会返回所有作品的id，但是没有作品信息，需要使用后续接口来获取

### 查询用户的绘画

- 方法名：illusts
- URL：`https://www.pixiv.net/ajax/user/${uid}/profile/illusts`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - is_first_page：是否为第一页。1 = 是 ，0 = 否。返回结果疑似没有区别
  - work_category： 作品类型，可选值：`illust`(插画)、`manga`(漫画)、`illustManga`(二者混合)。疑似不敏感，但是不能省略
  - ids[]：需要查询的作品id，可以传多个

### 查询用户绘画中使用的标签

- 方法名：illustTags
- URL：`https://www.pixiv.net/ajax/user/${uid}/illusts/tags`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id

### 查询用户的小说

- 方法名：profileNovels
- URL：`https://www.pixiv.net/ajax/user/${uid}/profile/novels`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - ids[]：需要查询的作品id，可以传多个

### 查询用户小说中使用的标签

- 方法名：novelsTags
- URL：`https://www.pixiv.net/ajax/user/${uid}/novels/tags`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id

### 查询用户发出约稿的作品

- 方法名：commissionRequestSent
- URL：`https://www.pixiv.net/ajax/commission/page/users/${uid}/request/sent`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id

## 用户收藏API - userBookmarkApi

### 查询用户收藏的绘画

- 方法名：illusts
- URL：`https://www.pixiv.net/ajax/user/${uid}/illusts/bookmarks`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
    - uid：(PATH传递)用户id
    - tag：带有的标签
    - offset： 跳过前面 x 个查询结果
    - limit：每次查询返回的结果条数上限，最大为100
    - rest：`show`公开的，`hide`不公开的(仅自己)
- 备注：tag参数写`未分類`可以查询到添加收藏时未提供标签(网页端/APP的默认操作)的作品

### 查询用户收藏的小说

- 方法名：novels
- URL：`https://www.pixiv.net/ajax/user/${uid}/novels/bookmarks`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
    - uid：(PATH传递)用户id
    - tag：带有的标签
    - offset： 跳过前面 x 个查询结果
    - limit：每次查询返回的结果条数上限，最大为100
    - rest：`show`公开的，`hide`不公开的(仅自己)
- 备注：tag参数写`未分類`可以查询到添加收藏时未提供标签(网页端/APP的默认操作)的作品

### 查询用户的收藏的绘画中使用的标签

- 方法名：illustTags
- URL：`https://www.pixiv.net/ajax/user/${uid}/illusts/bookmark/tags`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
    - uid：(PATH传递)用户id

### 查询用户的收藏小说中使用的标签

- 方法名：novelTags
- URL：`https://www.pixiv.net/ajax/user/${uid}/novels/bookmark/tags`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id

## 排行榜API - rankingApi

类名：`ApiRanking`

### 绘画榜

- 方法名：illust
- URL：`https://www.pixiv.net/ranking.php`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - p：页码
  - mode：模式，可取值：`daily`, `weekly`, `monthly`, `rookie`, `original`, `male`, `female`, `daily_r18`, `weekly_r18`, `male_r18`, `female_r18`
  - format：固定为`json`
  - content：正文类型，可取值：`all`,`illust`,`manga`,`ugoira`
  - date：(非必须)日期，格式：yyyyMMdd