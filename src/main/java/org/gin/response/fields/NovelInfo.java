package org.gin.response.fields;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 小说基础信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 09:28
 */
@Getter
@Setter
public class NovelInfo {
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

    Integer readingTime;

    Long seriesId;
    String seriesTitle;

    /**
     * 标签
     */
    List<String> tags;
    /**
     * 字数(文本数)
     */
    Integer textCount;
    /**
     * 标题
     */
    String title;
    /**
     * 更新时间
     */
    ZonedDateTime updateDate;
    String url;
    Long userId;
    String userName;
    /**
     * 字数(?)
     */
    Integer wordCount;


}
