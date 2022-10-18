package org.gin.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.OkHttpClient;
import org.gin.MapUtils;
import org.gin.params.CommentParam;
import org.gin.params.illustmanga.IllustsCommentRepliesParam;
import org.gin.params.illustmanga.IllustsCommentRootsParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.request.PixivRequestBody;
import org.gin.response.PixivResponse;
import org.gin.response.body.CommentBody;
import org.gin.response.body.illustmanga.IllustMangaCommentsBody;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;

import static org.gin.request.PixivUrl.createHttpUrl;

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
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illusts/comments/roots"),
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
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illusts/comments/replies"),
                client, pixivCookieToken
        );
    }

    /**
     * 发表评论
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustMangaCommentsBody>>
     * @since 2022/10/17 14:37
     */
    public static PixivRequest<PixivResponse<CommentBody>> comment(@NotNull CommentParam param,
                                                                   @NotNull PixivCookieToken pixivCookieToken,
                                                                   @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(Pixiv.DOMAIN + "/rpc/post_comment.php"),
                PixivRequestBody.createFormBody(param),
                client, pixivCookieToken
        );
    }

    /**
     * 删除评论
     * @param pid              作品id
     * @param commentId        评论id
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.CommentBody>>
     * @since 2022/10/17 14:54
     */
    public static PixivRequest<PixivResponse<String>> delComment(long pid,
                                                                 long commentId,
                                                                 @NotNull PixivCookieToken pixivCookieToken,
                                                                 @NotNull OkHttpClient client
    ) {
        final HashMap<String, Long> body = MapUtils.singleTon("i_id", pid);
        body.put("del_id", commentId);
        return new PixivRequest<>(
                createHttpUrl(Pixiv.DOMAIN + "/rpc_delete_comment.php"),
                PixivRequestBody.createFormBody(body),
                client, pixivCookieToken
        );
    }


}
