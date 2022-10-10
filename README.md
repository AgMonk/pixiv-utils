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