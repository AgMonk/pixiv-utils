package org.gin.api.groups;

import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.gin.api.PixivApi;
import org.gin.params.illustmanga.NovelsDiscoveryParam;
import org.gin.params.novel.NovelSearchParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.BookmarkDataRes;
import org.gin.response.body.illustmanga.DiscoveryIllustRes;
import org.gin.response.body.novel.NovelDetailRes;
import org.gin.response.body.novel.NovelSearchRes;
import org.jetbrains.annotations.NotNull;

import static org.gin.request.PixivUrl.createHttpUrl;

/**
 * 小说API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 14:35
 */
@RequiredArgsConstructor
public class NovelApi {
    private final PixivApi api;

    /**
     * 发现
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/10/21 9:14
     */
    public static PixivRequest<PixivResponse<DiscoveryIllustRes>> discovery(@NotNull NovelsDiscoveryParam param, @NotNull PixivCookieToken pixivCookieToken, @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, null, Pixiv.DOMAIN + "/ajax/discovery/novels"), client, pixivCookieToken);
    }

    /**
     * 查询小说的收藏状态
     * @param nid 小说id
     * @return org.gin.request.PixivRequest<org.gin.response.body.BookmarkDataRes>
     * @since 2022/11/16 14:59
     */
    public PixivRequest<BookmarkDataRes> bookmarkData(long nid) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novel/%d/bookmarkData", nid)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 查询小说详情
     * @param nid 小说id
     * @return org.gin.request.PixivRequest<NovelDetailRes>
     * @since 2022/11/16 15:00
     */
    public PixivRequest<NovelDetailRes> detail(long nid) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novel/" + nid)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 搜索
     * @param keywords 关键字
     * @param param    参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.novel.NovelSearchBody>>
     * @since 2022/11/16 15:00
     */
    public PixivRequest<NovelSearchRes> search(@NotNull String keywords, @NotNull NovelSearchParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/search/novels/" + keywords)
                .setParams(param)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

}
