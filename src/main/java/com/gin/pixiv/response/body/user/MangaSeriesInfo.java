package com.gin.pixiv.response.body.user;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 漫画系列信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 11:53
 **/
@Getter
@Setter
public class MangaSeriesInfo {
    /**
     * 说明
     */
    String caption;
    /**
     * 创建时间
     */
    ZonedDateTime createDate;
    /**
     * 作品描述
     */
    String description;
    /**
     * 系列第一件作品的pid
     */
    Long firstIllustId;
    /**
     * 系列的id
     */
    Long id;
    /**
     * 系列最后一件作品的pid
     */
    Long latestIllustId;
    /**
     * 标题
     */
    String title;
    /**
     * 总计作品数量
     */
    Integer total;
    /**
     * 更新时间
     */
    ZonedDateTime updateDate;
    /**
     * url
     */
    String url;
    /**
     * 用户id
     */
    Long userId;

}
