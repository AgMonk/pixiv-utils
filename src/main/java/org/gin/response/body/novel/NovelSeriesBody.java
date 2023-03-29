package org.gin.response.body.novel;

import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.enums.AiType;
import org.gin.pixiv.enums.RestrictLevel;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * 小说系列body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 10:36
 */
@Getter
@Setter
public class NovelSeriesBody {
    AiType aiType;
    String caption;
    Cover cover;
    /**
     * 创建时间
     */
    ZonedDateTime createDate;
    Long createdTimestamp;
    Integer displaySeriesContentCount;
    Long firstNovelId;
    Integer genreId;
    Long id;
    String language;
    Long lastPublishedContentTimestamp;
    Long latestNovelId;
    /**
     * 头像链接
     */
    String profileImageUrl;
    Integer publishedContentCount;
    Integer publishedReadingTime;
    Integer publishedTotalCharacterCount;
    Integer publishedTotalWordCount;
    /**
     * 分享文本
     */
    String shareText;
    /**
     * 标签
     */
    List<String> tags;
    String title;
    Integer total;
    /**
     * 更新时间
     */
    ZonedDateTime updateDate;
    Integer updatedTimestamp;
    Long userId;
    String userName;
    RestrictLevel xRestrict;

    @Getter
    @Setter
    public static class Cover {
        HashMap<String, String> urls;
    }
}
