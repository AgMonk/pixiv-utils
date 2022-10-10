package org.gin.response.fields;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 关注列表/搜索结果中的作品信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 17:08
 **/
@Data
public class ArtworkInfo {
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
    /**
     * 作品类型 插画=0 漫画=1 动图=2
     */
    Integer illustType;
    /**
     * 作品含有的图片数量
     */
    Integer pageCount;
    /**
     * 头像链接
     */
    String profileImageUrl;
    /**
     * 标签
     */
    List<String> tags;
    /**
     * 标题
     */
    String title;
    /**
     * 上传时间
     */
    ZonedDateTime uploadDate;
    /**
     * 图片url
     */
    String url;
    String userAccount;
    Long userId;

    String userName;
    /**
     * 宽
     */
    Integer width;
}
