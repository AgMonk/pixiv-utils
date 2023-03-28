package org.gin.pixiv.response.body.illust;

import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.enums.AiType;
import org.gin.pixiv.enums.IllustType;
import org.gin.pixiv.enums.RestrictLevel;
import org.gin.response.fields.BookmarkData;

import java.time.ZonedDateTime;

/**
 * 绘画作品的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 12:43
 */
@Getter
@Setter
public class BaseIllustBody {
    AiType aiType;
    /**
     * 网页标题
     */
    String alt;
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
     * 高
     */
    Integer height;
    /**
     * pid
     */
    Long id;
    IllustType illustType;
    /**
     * 标题
     */
    String title;

    /**
     * 上传时间
     */
    ZonedDateTime uploadDate;
    /**
     * 用户id
     */
    Long userId;
    /**
     * 用户昵称
     */
    String userName;

    /**
     * 宽
     */
    Integer width;
    /**
     * 限制级别
     */
    RestrictLevel xRestrict;
}   
