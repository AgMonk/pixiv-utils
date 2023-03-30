package com.gin.pixiv.response.body.illust;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gin.pixiv.response.body.tag.PixivTagInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绘画作品详情body
 * @author bx002
 */
@Getter
@Setter
public class IllustBodyDetail extends BaseIllustBody {
    /**
     * 收藏数
     */
    Integer bookmarkCount;
    /**
     * 评论数
     */
    Integer commentCount;
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
     * 喜欢数量
     */
    Integer likeCount;
    /**
     * 是否喜欢
     */
    Boolean likeData;
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
     * 图片url
     */
    ArtworkUrls urls;
    /**
     * 用户账号
     */
    String userAccount;
    /**
     * 浏览次数
     */
    Integer viewCount;
    /**
     * 用户的其他作品
     */
    Map<Long, IllustBodySimple> userIllusts;

    /**
     * 全部原图URL
     * @return 全部原图URL
     */
    @JsonIgnore
    public List<String> getOriginalUrls() {
        if (urls == null || pageCount == null) {
            return null;
        }

        final String originalUrl = urls.getOriginal();
        final ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            urls.add(originalUrl.replace("_p0", "_p" + i));
        }
        return urls;
    }

    @Getter
    @Setter
    public static class ArtworkUrls {
        String mini;
        String original;
        String regular;
        String small;
        String thumb;

        public String getZipUrl() {
            return original == null ? null : original
                    .replace("img-original", "img-zip-ugoira")
                    .replace("_ugoira0.jpg", "_ugoira1920x1080.zip")
                    .replace("_ugoira0.png", "_ugoira1920x1080.zip")
                    ;
        }
    }

    @Getter
    @Setter
    public static class FanRequest {
        HashMap<String, Serializable> collaborateStatus;
        /**
         * 约稿方
         */
        BaseUserInfo fan;
        /**
         * 约稿请求
         */
        FanRequestInfo request;

        @Getter
        @Setter
        static class FanRequestInfo {
            Long requestId;
        }
    }

    @Getter
    @Setter
    public static class BaseUserInfo {
        /**
         * 头像链接
         */
        String profileImg;
        /**
         * 用户id
         */
        Long userId;
        /**
         * 用户昵称
         */
        String userName;
    }
}