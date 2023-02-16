package org.gin.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/18 09:42
 */
@Data
public class RankingResponse {
    /**
     * 正文类型
     */
    String content;
    /**
     * 正文
     */
    List<RankingData> contents;
    String date;
    Integer page;
    @JsonProperty("rank_total")
    Integer rankTotal;

    @Data
    public static class RankingData {
        String attr;
        @JsonProperty("bookmark_id")
        Long bookmarkId;
        @JsonProperty("bookmark_illust_restrict")
        Integer bookmarkIllustRestrict;
        String date;
        Integer height;
        @JsonProperty("illust_id")
        Integer illustId;
        @JsonProperty("illust_page_count")
        Integer illustPageCount;
        @JsonProperty("illust_series")
        IllustSeries illustSeries;
        @JsonProperty("illust_type")
        Integer illustType;
        @JsonProperty("illust_upload_timestamp")
        Long illustUploadTimestamp;
        @JsonProperty("profile_img")
        String profileImg;
        Integer rank;
        @JsonProperty("rating_count")
        Integer ratingCount;
        List<String> tags = new ArrayList<>();
        String title;
        String url;
        @JsonProperty("user_id")
        Long userId;
        @JsonProperty("user_name")
        String userName;
        @JsonProperty("view_count")
        Integer viewCount;
        Integer width;
        @JsonProperty("yes_rank")
        Integer yesRank;

        @Data
        static class IllustSeries {
            @JsonProperty("illust_series_caption")
            String illustSeriesCaption;
            @JsonProperty("illust_series_content_count")
            Integer illustSeriesContentCount;
            @JsonProperty("illust_series_content_illust_id")
            Long illustSeriesContentIllustId;
            @JsonProperty("illust_series_content_order")
            Integer illustSeriesContentOrder;
            @JsonProperty("illust_series_create_datetime")
            LocalDateTime illustSeriesCreateDatetime;
            @JsonProperty("illust_series_id")
            Long illustSeriesId;
            @JsonProperty("illust_series_title")
            String illustSeriesTitle;
            @JsonProperty("illust_series_user_id")
            Long illustSeriesUserId;
            @JsonProperty("page_url")
            String pageUrl;

        }

    }
}
