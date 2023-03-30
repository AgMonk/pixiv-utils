package org.gin.pixiv.response.body.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.enums.AiType;
import org.gin.pixiv.enums.RestrictLevel;
import org.gin.response.fields.BookmarkData;

import java.time.ZonedDateTime;

/**
 * 所有作品的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/30 11:52
 */
@Getter
@Setter
public class BaseWorkBody {
    AiType aiType;
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
     * pid 或 nid
     */
    Long id;
    /**
     * 标题
     */
    String title;
    /**
     * 用户id
     */
    Long userId;
    /**
     * 用户昵称
     */
    String userName;
    /**
     * 限制级别
     */
    @JsonProperty("xRestrict")
    RestrictLevel xRestrict;

}   
