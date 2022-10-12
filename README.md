# pixiv-utils
Pixiv工具类

# 引入
```xml
<project>
    <repositories>
        <repository>
            <id>org.gin</id>
            <name>common-utils</name>
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

# Pixiv API

## 作品

### 查询详情

- URL：`https://www.pixiv.net/ajax/illust/${pid}`
- 请求方法：`GET`
- 参数含义：
  - pid：作品ID、pid

### 搜索

- URL：`https://www.pixiv.net/ajax/search/artworks/${keyword}`
- 请求方法：`GET`
- 传参方式：`QUERY`
- 参数结构：
  - keyword：关键字，中文需使用UTF-8编码
- 参数含义：
  - mode：模式，可选值：`all`,`r18`
  - lang：语言，简中为 `zh`
  - p：页码

## 收藏

收藏相关的操作，请求头中均需要添加字段 `x-csrf-token`，可以打开F12面板，进行一次操作查看请求头中发送的该字段

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

### 删除收藏

- URL：`https://www.pixiv.net/ajax/illusts/bookmarks/del `

- 请求方法：`POST`

- 传参方式：`FORM`

- 参数结构：

  ```json
  {
      "bookmark_id": 11111
  }
  ```

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

- 参数结构：

  ```json
  {
      "mode": "",
      "lang": "",
      "p": 1
  }
  ```

- 参数含义：

  - mode：模式，可选值：`all`,`r18`
  - lang：语言，简中为 `zh`
  - p：页码

### 关注作者

### 取关作者