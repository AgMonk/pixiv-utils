package org.gin.response;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(alternateNames = "rank_total")
    Integer rankTotal;

    @Data
    public static class RankingData {
        String attr;
        @JSONField(alternateNames = "bookmark_id")
        Long bookmarkId;
        @JSONField(alternateNames = "bookmark_illust_restrict")
        Integer bookmarkIllustRestrict;
        String date;
        Integer height;
        @JSONField(alternateNames = "illust_id")
        Integer illustId;
        @JSONField(alternateNames = "illust_page_count")
        Integer illustPageCount;
        @JSONField(alternateNames = "illust_series")
        IllustSeries illustSeries;
        @JSONField(alternateNames = "illust_type")
        Integer illustType;
        @JSONField(alternateNames = "illust_upload_timestamp")
        Long illustUploadTimestamp;
        @JSONField(alternateNames = "profile_img")
        String profileImg;
        Integer rank;
        @JSONField(alternateNames = "rating_count")
        Integer ratingCount;
        List<String> tags = new ArrayList<>();
        String title;
        String url;
        @JSONField(alternateNames = "user_id")
        Long userId;
        @JSONField(alternateNames = "user_name")
        String userName;
        @JSONField(alternateNames = "view_count")
        Integer viewCount;
        Integer width;
        @JSONField(alternateNames = "yes_rank")
        Integer yesRank;

        @Data
        static class IllustSeries {
            @JSONField(alternateNames = "illust_series_caption")
            String illustSeriesCaption;
            @JSONField(alternateNames = "illust_series_content_count")
            Integer illustSeriesContentCount;
            @JSONField(alternateNames = "illust_series_content_illust_id")
            Long illustSeriesContentIllustId;
            @JSONField(alternateNames = "illust_series_content_order")
            Integer illustSeriesContentOrder;
            @JSONField(alternateNames = "illust_series_create_datetime")
            LocalDateTime illustSeriesCreateDatetime;
            @JSONField(alternateNames = "illust_series_id")
            Long illustSeriesId;
            @JSONField(alternateNames = "illust_series_title")
            String illustSeriesTitle;
            @JSONField(alternateNames = "illust_series_user_id")
            Long illustSeriesUserId;
            @JSONField(alternateNames = "page_url")
            String pageUrl;

        }

    }
}
