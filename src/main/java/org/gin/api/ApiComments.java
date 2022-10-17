package org.gin.api;

import okhttp3.OkHttpClient;
import org.gin.params.illustmanga.IllustsCommentRepliesParam;
import org.gin.params.illustmanga.IllustsCommentRootsParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.illustmanga.IllustMangaCommentsBody;
import org.jetbrains.annotations.NotNull;

/**
 * 评论区接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:26
 */
public class ApiComments {
    /**
     * 查询作品评论(根)
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.LikeBody>>
     * @since 2022/10/15 16:23
     */
    public static PixivRequest<PixivResponse<IllustMangaCommentsBody>> illustsRoots(@NotNull IllustsCommentRootsParam param,
                                                                                    @NotNull PixivCookieToken pixivCookieToken,
                                                                                    @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/illusts/comments/roots"),
                client, pixivCookieToken
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
    public static PixivRequest<PixivResponse<IllustMangaCommentsBody>> illustsReplies(@NotNull IllustsCommentRepliesParam param,
                                                                                      @NotNull PixivCookieToken pixivCookieToken,
                                                                                      @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/illusts/comments/replies"),
                client, pixivCookieToken
        );
    }


    //todo 发表评论
}
