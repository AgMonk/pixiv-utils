package org.gin.pixiv.response.body.novel;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 13:00
 */
@Getter
@Setter
public class NovelBodySimple extends BaseNovelBody {
    /**
     * 系列id
     */
    Long seriesId;
    /**
     * 系列标题
     */
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
     * 更新时间
     */
    ZonedDateTime updateDate;
}
