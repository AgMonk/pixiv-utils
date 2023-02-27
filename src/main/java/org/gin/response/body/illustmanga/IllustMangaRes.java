package org.gin.response.body.illustmanga;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;
import org.gin.response.fields.ArtworkUrls;
import org.gin.response.fields.BookmarkData;
import org.gin.response.fields.FanRequest;
import org.gin.response.fields.PixivTagInfo;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 作品详情信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 15:27
 **/
@Getter
@Setter
public class IllustMangaRes extends PixivResponse<IllustMangaRes.IllustMangaBody> {
    @Data
    public static class IllustMangaBody {
        /**
         * ai 类型 ，1 = 常规图 ， 2 = ai 图
         */
        Integer aiType;
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
        /**
         * 用户账号
         */
        String userAccount;
        /**
         * 用户id
         */
        Long userId;
        /**
         * 用户昵称
         */
        String userName;
        /**
         * 浏览次数
         */
        Integer viewCount;
        /**
         * 宽
         */
        Integer width;

        /**
         * 全部原图URL
         * @return 全部原图URL
         */
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
    }
}
