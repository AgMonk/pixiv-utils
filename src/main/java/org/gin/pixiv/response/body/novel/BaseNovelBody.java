package org.gin.pixiv.response.body.novel;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.fields.BookmarkData;

import java.time.ZonedDateTime;

/**
 * 小说body的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 13:00
 */
@Getter
@Setter
public class BaseNovelBody {
    Integer bookmarkCount;
    /**
     * 收藏数据
     */
    BookmarkData bookmarkData;
    /**
     * 创建时间
     */
    ZonedDateTime createDate;
    /**
     * 作品描述
     */
    String description;
    /**
     * nid
     */
    Long id;
    /**
     * 头像链接
     */
    String profileImageUrl;
    /**
     * 阅读时长
     */
    Integer readingTime;
    /**
     * 标题
     */
    String title;
    String url;
    Long userId;
    String userName;
    /**
     * 字数(?)
     */
    Integer wordCount;

}   
