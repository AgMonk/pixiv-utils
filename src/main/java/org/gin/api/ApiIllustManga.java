package org.gin.api;

import okhttp3.OkHttpClient;
import org.gin.params.SearchParam;
import org.gin.params.SimpleParam;
import org.gin.request.*;
import org.gin.response.PixivResponse;
import org.gin.response.body.*;
import org.jetbrains.annotations.NotNull;

/**
 * 插画和漫画接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 15:52
 **/

public class ApiIllustManga {

    /**
     * 查询插画 / 漫画的详情信息
     * @param pid              作品id
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.ArtworkBody>>
     * @since 2022/10/14 17:07
     */
    public static PixivRequest<PixivResponse<ArtworkBody>> detail(int pid,
                                                                  @NotNull PixivCookieToken pixivCookieToken,
                                                                  @NotNull OkHttpClient client) {
        return new PixivRequest<>(PixivUrl.createHttpUrl(Pixiv.DOMAIN + "/ajax/illust/%d", pid)
                , client, pixivCookieToken);
    }

    /**
     * 搜索
     * @param keywords         关键字
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.SearchBody>>
     * @since 2022/10/14 17:28
     */
    public static PixivRequest<PixivResponse<SearchBody>> search(@NotNull String keywords,
                                                                 @NotNull SearchParam param,
                                                                 @NotNull PixivCookieToken pixivCookieToken,
                                                                 @NotNull OkHttpClient client) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/search/artworks/%s", keywords),
                client, pixivCookieToken
        );
    }

    /**
     * 喜欢作品
     * @param pid              作品id
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
     * @since 2022/10/15 16:06
     */
    public static PixivRequest<PixivResponse<LikeBody>> like(long pid,
                                                             @NotNull SimpleParam param,
                                                             @NotNull PixivCookieToken pixivCookieToken,
                                                             @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/illusts/like"),
                PixivRequestBody.createJsonBody("illust_id", pid),
                client, pixivCookieToken
        );
    }

    /**
     * 查询动图的其他信息
     * @param pid              作品id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UgoiraMetaBody>>
     * @since 2022/10/15 16:52
     */
    public static PixivRequest<PixivResponse<UgoiraMetaBody>> ugoiraMeta(long pid,
                                                                         @NotNull SimpleParam param,
                                                                         @NotNull PixivCookieToken pixivCookieToken,
                                                                         @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/illust/%d/ugoira_meta", pid),
                client, pixivCookieToken
        );
    }

    /**
     * 查询作品的收藏状态
     * @param pid              作品id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.BookmarkDataBody>>
     * @since 2022/10/15 17:19
     */
    public static PixivRequest<PixivResponse<BookmarkDataBody>> bookmarkData(long pid,
                                                                             @NotNull SimpleParam param,
                                                                             @NotNull PixivCookieToken pixivCookieToken,
                                                                             @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/illust/%d/bookmarkData", pid),
                client, pixivCookieToken
        );
    }


}
