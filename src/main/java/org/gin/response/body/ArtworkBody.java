package org.gin.response.body;

import lombok.Data;
import org.gin.response.fields.ArtworkUrls;
import org.gin.response.fields.BookmarkData;
import org.gin.response.fields.FanRequest;
import org.gin.response.fields.PixivTagInfo;

import java.time.ZonedDateTime;

/**
 * 作品详情信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 15:27
 **/
@Data
public class ArtworkBody {
    /**
     * 网页标题
     */
    String alt;
    /**
     * 收藏数
     */
    Integer bookmarkCount;
    /**
     * 收藏数据
     */
    BookmarkData bookmarkData;
    /**
     * 评论数
     */
    Integer commentCount;
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
     * 作品描述
     */
    String illustComment;
    /**
     * pid
     */
    Long illustId;
    /**
     * 作品标题
     */
    String illustTitle;
    /**
     * 作品类型 插画=0 漫画=1 动图=2
     */
    Integer illustType;
    /**
     * 喜欢数量
     */
    Integer likeCount;
    /**
     * 作品含有的图片数量
     */
    Integer pageCount;
    /**
     * 约稿信息(如果是约稿作品)
     */
    FanRequest request;
    /**
     * 标签
     */
    PixivTagInfo tags;
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
    ArtworkUrls urls;
    String userAccount;
    Long userId;

    String userName;
    /**
     * 浏览次数
     */
    Integer viewCount;
    /**
     * 宽
     */
    Integer width;
}
