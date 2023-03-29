package org.gin.pixiv.response.body.novel;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 小说详情Body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 11:55
 */
@Getter
@Setter
public class NovelBodyDetail extends BaseNovelBody {
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
    String language;
    Integer likeCount;
    /**
     * 是否喜欢
     */
    Boolean likeData;
    Integer markerCount;
    /**
     * 页数
     */
    Integer pageCount;

    SeriesNavData seriesNavData;

    TagData tags;

    /**
     * 上传时间
     */
    ZonedDateTime uploadDate;

    Integer viewCount;

    @Getter
    @Setter
    public static class TagData {
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
    public static class SeriesNavData {
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
