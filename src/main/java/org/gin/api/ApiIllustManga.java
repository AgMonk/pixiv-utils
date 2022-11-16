package org.gin.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.OkHttpClient;
import org.gin.params.SimpleParam;
import org.gin.params.illustmanga.IllustMangaSearchParam;
import org.gin.params.illustmanga.IllustsDiscoveryParam;
import org.gin.params.illustmanga.RecommendIllustsParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.gin.response.body.BookmarkDataRes;
import org.gin.response.body.LikeRes;
import org.gin.response.body.illustmanga.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;

import static org.gin.request.PixivRequestBody.createJsonBody;
import static org.gin.request.PixivUrl.createHttpUrl;

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
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.ArtworkBody>>
     * @since 2022/10/14 17:07
     */
    public static PixivRequest<PixivResponse<IllustMangaRes>> detail(long pid,
                                                                     @NotNull PixivCookieToken pixivCookieToken,
                                                                     @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(Pixiv.DOMAIN + "/ajax/illust/%d", pid)
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
    public static PixivRequest<PixivResponse<IllustMangaSearchRes>> search(@NotNull String keywords,
                                                                           @NotNull IllustMangaSearchParam param,
                                                                           @NotNull PixivCookieToken pixivCookieToken,
                                                                           @NotNull OkHttpClient client) {
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/search/artworks/%s", keywords),
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
    public static PixivRequest<PixivResponse<LikeRes>> like(long pid,
                                                            @NotNull SimpleParam param,
                                                            @NotNull PixivCookieToken pixivCookieToken,
                                                            @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illusts/like"),
                createJsonBody("illust_id", pid),
                client, pixivCookieToken
        );
    }

    /**
     * 查询动图的其他信息
     * @param pid              作品id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.UgoiraMetaBody>>
     * @since 2022/10/15 16:52
     */
    public static PixivRequest<PixivResponse<UgoiraMetaRes>> ugoiraMeta(long pid,
                                                                        @NotNull SimpleParam param,
                                                                        @NotNull PixivCookieToken pixivCookieToken,
                                                                        @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illust/%d/ugoira_meta", pid),
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
    public static PixivRequest<PixivResponse<BookmarkDataRes>> bookmarkData(long pid,
                                                                            @NotNull SimpleParam param,
                                                                            @NotNull PixivCookieToken pixivCookieToken,
                                                                            @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illust/%d/bookmarkData", pid),
                client, pixivCookieToken
        );
    }

    /**
     * 发现
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/10/21 9:14
     */
    public static PixivRequest<PixivResponse<DiscoveryIllustRes>> discovery(@NotNull IllustsDiscoveryParam param,
                                                                            @NotNull PixivCookieToken pixivCookieToken,
                                                                            @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/discovery/artworks"),
                client, pixivCookieToken
        );
    }

    /**
     * 查询推荐作品
     * @param pid              基准作品pid
     * @param limit            作品数量
     * @param lang             语言
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/11/1 9:43
     */
    public static PixivRequest<PixivResponse<IllustRecommendInitRes>> recommendInit(long pid, int limit, String lang,
                                                                                    @NotNull PixivCookieToken pixivCookieToken,
                                                                                    @NotNull OkHttpClient client
    ) {
        final HashMap<String, Object> map = new HashMap<>(2);
        map.put("limit", limit);
        map.put("lang", lang);
        return new PixivRequest<>(
                createHttpUrl(map, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illust/%d/recommend/init", pid),
                client, pixivCookieToken
        );
    }

    /**
     * 查询推荐作品2
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/11/1 9:48
     */
    public static PixivRequest<PixivResponse<IllustRecommendRes>> recommendIllusts(
            @NotNull RecommendIllustsParam param,
            @NotNull PixivCookieToken pixivCookieToken,
            @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/illust/recommend/illusts"),
                client, pixivCookieToken
        );
    }


}
