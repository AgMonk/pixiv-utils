package org.gin.api.groups;

import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.body.novel.NovelContentTitleRes;
import org.gin.response.body.novel.NovelSeriesContentRes;
import org.gin.response.body.novel.NovelSeriesRes;

/**
 * 小说系列API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 15:56
 */
@RequiredArgsConstructor
public class NovelSeriesApi {
    private final PixivApi api;

    /**
     * 查询系列中作品的基础信息
     * @param seriesId 系列id
     * @return org.gin.request.PixivRequest<NovelSeriesContentRes>
     * @since 2022/11/16 16:07
     */
    public PixivRequest<NovelSeriesContentRes> contents(long seriesId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novel/series_content/" + seriesId)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 查询小说系列
     * @param seriesId 系列id
     * @return org.gin.request.PixivRequest<NovelSeriesRes>
     * @since 2022/11/16 16:07
     */
    public PixivRequest<NovelSeriesRes> info(long seriesId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novel/series/" + seriesId)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 查询系列的各篇标题
     * @param seriesId 系列id
     * @return org.gin.request.PixivRequest<org.gin.response.body.novel.NovelContentTitleRes>
     * @since 2022/11/16 16:07
     */
    public PixivRequest<NovelContentTitleRes> titles(long seriesId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novel/series/%d/content_titles", seriesId)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());

    }


}
