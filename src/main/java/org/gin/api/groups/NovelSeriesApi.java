package org.gin.api.groups;

import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.params.novel.NovelSeriesContentParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.body.novel.*;
import org.gin.response.convertor.Convertor;
import org.gin.response.fields.NovelSeriesContent;

import java.util.List;

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
    public PixivRequest<NovelSeriesContentRes> contents(long seriesId, NovelSeriesContentParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novel/series_content/" + seriesId)
                .setParams(param)

                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, NovelSeriesContentRes.class));
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

                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, NovelSeriesRes.class));
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

                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, NovelContentTitleRes.class));

    }

    public void zTest() {
        long seriesId = 8174474;
        zTestInfo(seriesId);
        zTestContents(seriesId);
        zTestTitles(seriesId);
    }

    private void zTestContents(long seriesId) {
        contents(seriesId, new NovelSeriesContentParam(1, 5)).async(res -> {
            final List<NovelSeriesContent> contents = res.getBody().getSeriesContents();
            System.out.printf("[小说系列各篇] sid = %d 标题1: %s \n", seriesId, contents.get(0).getTitle());
        });
    }

    private void zTestInfo(long seriesId) {
        info(seriesId).async(res -> {
            final NovelSeriesBody body = res.getBody();
            System.out.printf("[小说系列] sid = %d 标题: %s \n", seriesId, body.getTitle());
        });
    }

    private void zTestTitles(long seriesId) {
        titles(seriesId).async(res -> {
            final List<NovelContentTitleBody> titles = res.getBody();
            System.out.printf("[小说系列标题] sid = %d 标题1: %s \n", seriesId, titles.get(0).getTitle());
        });
    }


}
