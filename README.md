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
    
    <dependency>
        <groupId>org.gin</groupId>
        <artifactId>pixiv-utils</artifactId>
        <version>1.0.0</version>
    </dependency>
</project>
```

# 使用方法

`PixivRequestAsync` 和 `PixivRequestSync` 类下有两套名称相同的方法,分别为异步和同步请求方法。

- 查询作品详情: detail
- 查询关注作者最新作品: followLatest
- 收藏作品：bmkAdd
- 删除收藏：bmkDel
- 删除收藏（批量）：bmkDel（重载）
- 搜索作品：search
- 关注用户：followUser
- 取关用户：unFollowUser
- 查询用户信息：userInf

# Pixiv API

以`POST`发出的`修改`操作，请求头中均需要添加字段 `x-csrf-token`，可以打开F12面板，进行一次操作查看请求头中发送的该字段

## 作品

### 查询详情

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



## 收藏



### 添加收藏

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

### 删除收藏

- URL：`https://www.pixiv.net/ajax/illusts/bookmarks/del `
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：

  - bookmark_id：收藏id，从作品信息的bookmarkData字段中获取

### 删除收藏(批量)

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

## 关注

### 查询关注作者的最新作品

- URL：`https://www.pixiv.net/ajax/follow_latest/illust `
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - mode：模式，可选值：`all`,`r18`
  - lang：语言，简中为 `zh`
  - p：页码

### 关注作者

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

- URL：`https://www.pixiv.net/rpc_group_setting.php`
- 请求方法：`POST`
- 传参方式：`FORM`
- 参数含义：

  - mode：固定为`del`
  - type：固定为`bookuser`
  - id：需要取关的用户id

## 用户

### 查询用户信息

- URL：`https://www.pixiv.net/ajax/user/${uid}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - full：固定为`1`，传递该参数将获得额外信息
  - lang：语言，简中为 `zh`

### 查询用户的作品概况

- URL：`https://www.pixiv.net/ajax/user/${uid}/profile/all`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - lang：语言，简中为 `zh`
- 响应：这里会返回所有作品的id，但是没有作品信息，需要使用后续接口来获取

### 查询用户的作品信息

- URL：`https://www.pixiv.net/ajax/user/${uid}/profile/illusts`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - lang：语言，简中为 `zh`
  - is_first_page：是否为第一页。1 = 是 ，0 = 否。也许不需要传递
  - work_category： 作品类型，可选值：`illust`(插画)、`manga`(漫画)、`illustManga`(二者混合)
  - ids：需要查询的作品id，可以传多个

### 查询用户的收藏作品

- URL：`https://www.pixiv.net/ajax/user/${uid}/illusts/bookmarks`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - lang：语言，简中为 `zh`
  - tag：带有的标签
  - offset： 跳过前面 x 个查询结果
  - limit：每次查询返回的结果条数上限
  - rest：`show`公开的，`hide`不公开的(仅自己)

- 备注：tag参数写`未分類`可以查询到添加收藏时未提供标签(网页端/APP的默认操作)的作品

### 查询用户的收藏作品中使用的标签

- URL：`https://www.pixiv.net/ajax/user/${uid}/illusts/bookmark/tags`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数含义：
  - uid：(PATH传递)用户id
  - lang：语言，简中为 `zh`