package org.gin.response.fields;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 小说基础信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 09:28
 */
@Data
public class NovelSeriesContent {
    Integer bookmarkCount;
    /**
     * 收藏数据
     */
    BookmarkData bookmarkData;

    Series series;

    /**
     * 再上传时间
     */
    @JsonProperty("reuploadTimestamp")
    Long reUploadTimestamp;
    /**
     * 作品描述
     */
    String commentHtml;
    /**
     * nid
     */
    Long id;
    /**
     * 封面链接
     */
    String url;

    Integer readingTime;

    /**
     * 标签
     */
    List<String> tags;
    /**
     * 字数(文本数)
     */
    Integer characterCount;
    Integer textLength;
    /**
     * 标题
     */
    String title;
    /**
     * 上传时间
     */
    Long uploadTimestamp;
    Long userId;
    /**
     * 字数(?)
     */
    Integer wordCount;

    @Data
    static class Series {
        Integer contentOrder;
        Long id;
        Integer viewableType;
    }
}
