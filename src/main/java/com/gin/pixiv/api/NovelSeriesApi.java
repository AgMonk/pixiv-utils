package com.gin.pixiv.api;

import com.gin.pixiv.call.PixivCallStandard;
import com.gin.pixiv.call.PixivCallType;
import com.gin.pixiv.callback.StandardCallback;
import com.gin.pixiv.callback.TypeCallback;
import com.gin.pixiv.main.PixivClient;
import com.gin.pixiv.params.novel.NovelSeriesContentParam;
import com.gin.pixiv.response.body.novel.NovelBodySeriesContent;
import com.gin.pixiv.response.body.novel.NovelContentTitleRes;
import com.gin.pixiv.response.body.novel.NovelSeriesBody;
import lombok.RequiredArgsConstructor;

/**
 * 小说系列API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 16:26
 */
@RequiredArgsConstructor
public class NovelSeriesApi {
    private final PixivClient client;

    /**
     * 查询系列中作品的基础信息
     * @param seriesId 系列id
     * @param param    参数
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.novel.NovelBodySeriesContent>
     * @since 2023/3/29 17:56
     */
    public PixivCallStandard<NovelBodySeriesContent> getContents(long seriesId, NovelSeriesContentParam param) {
        return client.standard("/ajax/novel/series_content/" + seriesId, NovelBodySeriesContent.class, param);
    }

    /**
     * 查询小说系列详情
     * @param seriesId 系列id
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.novel.NovelSeriesBody>
     * @since 2023/3/29 17:55
     */
    public PixivCallStandard<NovelSeriesBody> getDetail(long seriesId) {
        return client.standard("/ajax/novel/series/" + seriesId, NovelSeriesBody.class);
    }

    /**
     * 查询系列的各篇标题
     * @param seriesId 系列id
     * @return org.gin.pixiv.call.PixivCallType<org.gin.pixiv.response.body.novel.NovelContentTitleRes>
     * @author bx002
     * @since 2023/3/29 17:55
     */
    public PixivCallType<NovelContentTitleRes> getTitles(long seriesId) {
        return client.type(String.format("/ajax/novel/series/%d/content_titles", seriesId), NovelContentTitleRes.class);
    }

    public void zTest() {
        long seriesId = 8174474;
        zTestInfo(seriesId);
        zTestContents(seriesId);
        zTestTitles(seriesId);
    }

    private void zTestContents(long seriesId) {
        getContents(seriesId, new NovelSeriesContentParam(1, 5)).async(new StandardCallback<NovelBodySeriesContent>() {
            @Override
            public void onSuccess(NovelBodySeriesContent body) {
                System.out.printf("[小说系列各篇] sid = %d 标题1: %s \n", seriesId, body.getThumbnails().getNovel().get(0).getTitle());

            }
        });
    }

    private void zTestInfo(long seriesId) {
        getDetail(seriesId).async(new StandardCallback<NovelSeriesBody>() {
            @Override
            public void onSuccess(NovelSeriesBody body) {
                System.out.printf("[小说系列] sid = %d 标题: %s \n", seriesId, body.getTitle());

            }
        });
    }

    private void zTestTitles(long seriesId) {
        getTitles(seriesId).async(new TypeCallback<NovelContentTitleRes>() {
            @Override
            public void onSuccess(NovelContentTitleRes body) {
                System.out.printf("[小说系列标题] sid = %d 标题1: %s \n", seriesId, body.getBody().get(0).getTitle());

            }
        });
    }
}
