package org.gin.api.groups;

import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.emuns.PixivMode;
import org.gin.params.illustmanga.NovelsDiscoveryParam;
import org.gin.params.novel.NovelSearchParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrlBuilder;
import org.gin.response.body.BookmarkDataRes;
import org.gin.response.body.illustmanga.DiscoveryRes;
import org.gin.response.body.illustmanga.IllustMangaFollowLatestRes;
import org.gin.response.body.novel.NovelDetailRes;
import org.gin.response.body.novel.NovelSearchRes;
import org.gin.response.convertor.Convertor;
import org.gin.response.fields.BookmarkData;
import org.gin.response.fields.NovelInfo;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 小说API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 14:35
 */
@SuppressWarnings("SameParameterValue")
@RequiredArgsConstructor
public class NovelApi {
    private final PixivApi api;

    /**
     * 查询小说的收藏状态
     * @param nid 小说id
     * @return org.gin.request.PixivRequest<org.gin.response.body.BookmarkDataRes>
     * @since 2022/11/16 14:59
     */
    public PixivRequest<BookmarkDataRes> bookmarkData(long nid) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/novel/%d/bookmarkData", nid)

                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, BookmarkDataRes.class));
    }

    /**
     * 查询小说详情
     * @param nid 小说id
     * @return org.gin.request.PixivRequest<NovelDetailRes>
     * @since 2022/11/16 15:00
     */
    public PixivRequest<NovelDetailRes> detail(long nid) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/novel/" + nid)

                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, NovelDetailRes.class));
    }

    /**
     * 发现
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/10/21 9:14
     */
    public PixivRequest<DiscoveryRes> discovery(@NotNull NovelsDiscoveryParam param) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/discovery/novels")
                .setParams(param)

                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, DiscoveryRes.class));
    }

    /**
     * 查询关注作者的最新小说
     * @param page 页码
     * @param mode 模式
     * @return org.gin.request.PixivRequest<org.gin.response.body.illustmanga.IllustMangaFollowLatestRes>
     * @since 2022/11/16 16:39
     */
    public PixivRequest<IllustMangaFollowLatestRes> latest(int page, @NotNull PixivMode mode) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/follow_latest/novel")
                .addParam("page", page)
                .addParam("mode", mode.name())

                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, IllustMangaFollowLatestRes.class));
    }

    /**
     * 搜索
     * @param keywords 关键字
     * @param param    参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.novel.NovelSearchBody>>
     * @since 2022/11/16 15:00
     */
    public PixivRequest<NovelSearchRes> search(@NotNull String keywords, @NotNull NovelSearchParam param) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/search/novels/" + keywords)
                .setParams(param)

                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, NovelSearchRes.class));
    }

    public void zTest() {
        long nid = 15809265;
        zTestLatest(1, PixivMode.all);
        zTestBookmarkData(nid);
        zTestDetail(nid);
        zTestDiscovery(nid);
        zTestSearch("RO635");
    }

    void zTestLatest(int page, @NotNull PixivMode mode) {
        latest(page, mode).async(res -> {
            final List<NovelInfo> data = res.getBody().getThumbnails().getNovel();
            System.out.printf("[最新小说] page: %d 结果 %d 个 \n", page, data.size());
        });
    }

    private void zTestBookmarkData(long nid) {
        bookmarkData(nid).async(res -> {
            final BookmarkData bookmarkData = res.getBody().getBookmarkData();
            if (bookmarkData != null) {
                System.out.printf("[收藏数据] nid = %d 收藏id = %d\n", nid, bookmarkData.getId());
            } else {
                System.out.println("未收藏 nid = " + nid);
            }
        });
    }

    private void zTestDetail(long nid) {
        detail(nid).async(res -> {
            final ZonedDateTime createDate = res.getBody().getCreateDate();
            System.out.printf("[小说详情] nid: %d 创建于 %s \n", nid, createDate);
        });
    }

    private void zTestDiscovery(long nid) {
        discovery(new NovelsDiscoveryParam(nid)).async(res -> {
            final List<NovelInfo> data = res.getBody().getThumbnails().getNovel();
            System.out.printf("[发现小说] nid: %d 结果 %d 个 \n", nid, data.size());
        });
    }

    private void zTestSearch(String keywords) {
        search(keywords, new NovelSearchParam()).async(res -> {
            final List<NovelInfo> data = res.getBody().getNovel().getData();
            System.out.printf("[搜索小说] 关键字: %s 结果 %d 个 \n", keywords, data.size());
        });
    }
}
