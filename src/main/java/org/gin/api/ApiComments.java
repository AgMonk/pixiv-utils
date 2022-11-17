package org.gin.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.OkHttpClient;
import org.gin.params.illustmanga.CommentRepliesParam;
import org.gin.params.illustmanga.IllustsCommentRootsParam;
import org.gin.params.illustmanga.NovelCommentRootsParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.gin.response.body.comment.CommentsBody;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static org.gin.request.PixivUrl.createHttpUrl;

/**
 * 评论区接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:26
 */
public class ApiComments {

    /**
     * 绘画相关
     */
    public static class Illust {
        /**
         * 查询作品评论(根)
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.LikeBody>>
         * @since 2022/10/15 16:23
         */
        public static PixivRequest<PixivResponse<CommentsBody>> roots(@NotNull IllustsCommentRootsParam param,
                                                                      @NotNull PixivCookieToken pixivCookieToken,
                                                                      @NotNull OkHttpClient client
        ) {
            return new PixivRequest<>(
                    createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illusts/comments/roots"),
                    client
            );
        }

        /**
         * 查询作品评论楼中楼
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustCommentsBody>>
         * @since 2022/10/15 16:46
         */
        public static PixivRequest<PixivResponse<CommentsBody>> replies(@NotNull CommentRepliesParam param,
                                                                        @NotNull PixivCookieToken pixivCookieToken,
                                                                        @NotNull OkHttpClient client
        ) {
            return new PixivRequest<>(
                    createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illusts/comments/replies"),
                    client
            );
        }
    }

    /**
     * 小说相关
     */
    public static class Novel {
        /**
         * 查询作品评论(根)
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.LikeBody>>
         * @since 2022/10/15 16:23
         */
        public static PixivRequest<PixivResponse<CommentsBody>> roots(@NotNull NovelCommentRootsParam param,
                                                                      @NotNull PixivCookieToken pixivCookieToken,
                                                                      @NotNull OkHttpClient client
        ) {
            return new PixivRequest<>(
                    createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/novels/comments/roots"),
                    client
            );
        }

        /**
         * 查询作品评论楼中楼
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustCommentsBody>>
         * @since 2022/10/15 16:46
         */
        public static PixivRequest<PixivResponse<CommentsBody>> replies(@NotNull CommentRepliesParam param,
                                                                        @NotNull PixivCookieToken pixivCookieToken,
                                                                        @NotNull OkHttpClient client
        ) {
            return new PixivRequest<>(
                    createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/novels/comments/replies"),
                    client
            );
        }
    }


}
