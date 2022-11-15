package org.gin.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.params.SimpleParam;
import org.gin.params.illustmanga.NovelsDiscoveryParam;
import org.gin.params.novel.NovelSearchParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.gin.response.body.BookmarkDataBody;
import org.gin.response.body.illustmanga.DiscoveryBody;
import org.gin.response.body.novel.NovelBody;
import org.gin.response.body.novel.NovelSearchBody;
import org.gin.response.body.novel.NovelSeriesBody;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static org.gin.request.PixivUrl.createHttpUrl;

/**
 * 小说接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 09:19
 */
public class ApiNovel {
    /**
     * 查询小说详情
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.FollowLatestBody>>
     * @since 2022/10/17 9:23
     */
    public static PixivRequest<PixivResponse<NovelBody>> detail(long nid,
                                                                @NonNull SimpleParam param,
                                                                @NonNull PixivCookieToken pixivCookieToken,
                                                                @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/novel/" + nid)
                , client, pixivCookieToken);
    }

    /**
     * 搜索
     * @param keywords         关键字
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < ?>>
     * @since 2022/10/17 10:30
     */
    public static PixivRequest<PixivResponse<NovelSearchBody>> search(@NotNull String keywords,
                                                                      @NotNull NovelSearchParam param,
                                                                      @NotNull PixivCookieToken pixivCookieToken,
                                                                      @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/search/novels/%s", keywords), client, pixivCookieToken);
    }


    /**
     * 查询小说的收藏状态
     * @param nid              小说id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.BookmarkDataBody>>
     * @since 2022/10/15 17:19
     */
    public static PixivRequest<PixivResponse<BookmarkDataBody>> bookmarkData(long nid,
                                                                             @NotNull SimpleParam param,
                                                                             @NotNull PixivCookieToken pixivCookieToken,
                                                                             @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/novel/%d/bookmarkData", nid),
                client, pixivCookieToken
        );
    }

    /**
     * 查询小说系列
     * @param seriesId         小说系列id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.novel.NovelSeriesBody>>
     * @since 2022/10/17 10:49
     */
    public static PixivRequest<PixivResponse<NovelSeriesBody>> series(long seriesId,
                                                                      @NotNull SimpleParam param,
                                                                      @NotNull PixivCookieToken pixivCookieToken,
                                                                      @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/novel/series/" + seriesId), client, pixivCookieToken);
    }

    /**
     * 发现
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/10/21 9:14
     */
    public static PixivRequest<PixivResponse<DiscoveryBody>> discovery(@NotNull NovelsDiscoveryParam param,
                                                                       @NotNull PixivCookieToken pixivCookieToken,
                                                                       @NotNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                createHttpUrl(param, null, Pixiv.DOMAIN + "/ajax/discovery/novels"),
                client, pixivCookieToken
        );
    }
}
