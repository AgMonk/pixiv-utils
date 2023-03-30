package com.gin.pixiv.response.body.novel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.pixiv.enums.AiType;
import com.gin.pixiv.enums.RestrictLevel;
import com.gin.pixiv.response.body.common.BookmarkData;
import com.gin.pixiv.response.body.common.Thumbnails;
import com.gin.pixiv.response.body.tag.TagTranslation;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

/**
 * 小说系列基础信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 16:33
 */
@Getter
@Setter
public class NovelBodySeriesContent {
    //todo illustSeries

    Page page;

    //todo requests

    HashMap<String, TagTranslation> tagTranslation;

    Thumbnails thumbnails;

    //todo users


    @Getter
    @Setter
    public static class Page {
        List<SeriesContent> seriesContents;
    }

    @Getter
    @Setter
    public static class Series {
        Integer contentOrder;
        Long id;
        Integer viewableType;
    }

    @Getter
    @Setter
    public static class SeriesContent {
        AiType aiType;
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
         * 作品描述
         */
        String commentHtml;
        Long id;
        /**
         * 阅读时长
         */
        Integer readingTime;
        /**
         * 再上传时间
         */
        @JsonProperty("reuploadTimestamp")
        Long reUploadTimestamp;

        Series series;
        /**
         * 标签
         */
        List<String> tags;

        Integer textLength;
        /**
         * 标题
         */
        String title;
        /**
         * 上传时间
         */
        Long uploadTimestamp;
        /**
         * 封面链接
         */
        @JsonProperty("url")
        String coverUrl;
        Long userId;
        /**
         * 字数(?)
         */
        Integer wordCount;
        /**
         * 限制级别
         */
        @JsonProperty("xRestrict")
        RestrictLevel xRestrict;

    }


}   
