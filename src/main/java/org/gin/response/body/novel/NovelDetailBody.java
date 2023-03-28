package org.gin.response.body.novel;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.fields.BookmarkData;

import java.time.ZonedDateTime;
import java.util.List;


/**
 * 小说body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 09:42
 */
@Getter
@Setter
public class NovelDetailBody {
    Integer bookmarkCount;
    /**
     * 收藏数据
     */
    BookmarkData bookmarkData;
    /**
     * 字数(文本数)
     */
    Integer characterCount;
    /**
     * 评论数
     */
    Integer commentCount;
    /**
     * 是否关闭评论
     */
    Integer commentOff;
    /**
     * 正文
     */
    String content;
    /**
     * 封面url
     */
    String coverUrl;
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
    String language;
    Integer likeCount;
    /**
     * 是否喜欢
     */
    Boolean likeData;
    Integer markerCount;
    Integer pageCount;
    /**
     * 头像链接
     */
    String profileImageUrl;
    Integer readingTime;
    SeriesNavData seriesNavData;

    TagData tags;
    /**
     * 标题
     */
    String title;
    /**
     * 上传时间
     */
    ZonedDateTime uploadDate;
    String url;
    Long userId;
    String userName;
    Integer viewCount;
    /**
     * 字数(?)
     */
    Integer wordCount;

    @Getter
    @Setter
    static class TagData {
        Boolean isLocked;
        List<Tag> tags;
        Boolean writable;

        @Getter
        @Setter
        static class Tag {
            Boolean deletable;
            Boolean locked;
            String tag;
            Long userId;
            String userName;
        }
    }

    @Getter
    @Setter
    static class SeriesNavData {
        Boolean isConcluded;
        Boolean isNotifying;
        Boolean isReplaceable;
        Boolean isWatched;
        Link next;
        Integer order;
        Link prev;
        Long seriesId;
        String seriesType;
        String title;

        @Getter
        @Setter
        static class Link {
            Boolean available;
            Long id;
            Integer order;
            String title;
        }
    }
}
