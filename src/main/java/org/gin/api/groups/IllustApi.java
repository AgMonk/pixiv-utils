package org.gin.api.groups;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import org.gin.api.PixivApi;
import org.gin.emuns.PixivMode;
import org.gin.params.illustmanga.IllustMangaSearchParam;
import org.gin.params.illustmanga.IllustsDiscoveryParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.body.BookmarkDataRes;
import org.gin.response.body.LikeRes;
import org.gin.response.body.illustmanga.*;
import org.gin.response.callback.BaseCallback;
import org.gin.response.fields.ArtworkInfo;
import org.gin.response.fields.BookmarkData;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static org.gin.request.PixivRequestBody.createJsonBody;

/**
 * 绘画API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 15:52
 **/
@RequiredArgsConstructor
public class IllustApi {
    private final PixivApi api;

    /**
     * 查询绘画的收藏状态
     * @param pid 绘画id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.BookmarkDataBody>>
     * @since 2022/10/15 17:19
     */
    public PixivRequest<BookmarkDataRes> bookmarkData(long pid) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illust/%d/bookmarkData", pid)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 查询绘画的详情信息
     * @param pid 绘画id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.ArtworkBody>>
     * @since 2022/10/14 17:07
     */
    public PixivRequest<IllustMangaRes> detail(long pid) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illust/" + pid)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 发现绘画
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/10/21 9:14
     */
    public PixivRequest<DiscoveryRes> discovery(@NotNull IllustsDiscoveryParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/discovery/artworks")
                .setParams(param)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 查询关注作者的最新绘画
     * @param page 页码
     * @param mode 模式 可选值: all r18
     * @return org.gin.request.PixivRequest<org.gin.response.body.illustmanga.IllustMangaFollowLatestRes>
     * @since 2022/11/16 14:11
     */
    public PixivRequest<IllustMangaFollowLatestRes> latest(int page, @NotNull PixivMode mode) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/follow_latest/illust")
                .addParam("page", page)
                .addParam("mode", mode.name())
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 喜欢绘画
     * @param pid 绘画id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
     * @since 2022/10/15 16:06
     */
    public PixivRequest<LikeRes> like(long pid) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illusts/like")
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, createJsonBody("illust_id", pid), api.getClient(), api.getCookieToken());
    }

    /**
     * 查询推荐绘画2
     * @param ids 基准id，可以直接使用 recommendInit 方法的返回结果
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/11/1 9:48
     */
    public PixivRequest<IllustRecommendRes> recommendIllusts(@NotNull List<Long> ids) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illust/recommend/illusts")
                .addParam("illust_ids[]", ids)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 查询推荐绘画
     * @param pid   基准绘画pid
     * @param limit 绘画数量
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.DiscoveryBody>>
     * @since 2022/11/1 9:43
     */
    public PixivRequest<IllustRecommendInitRes> recommendInit(long pid, int limit) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illust/%d/recommend/init", pid)
                .addParam("limit", limit)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 搜索
     * @param keywords 关键字
     * @param param    参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.SearchBody>>
     * @since 2022/10/14 17:28
     */
    public PixivRequest<IllustMangaSearchRes> search(@NotNull String keywords, @NotNull IllustMangaSearchParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/search/artworks/" + keywords)
                .setParams(param)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    public void test() {
        long pid = 99147997L;
        long ugoiraPid = 102814610;

        testBookmarkData(pid);
        testDetail(pid);
        testDiscovery(pid);
        testLike(pid);
        testRecommendIllusts(pid);
        testRecommendInit(pid, 20);
        testSearch("RO635");
        testUgoiraMeta(ugoiraPid);
        testLatest(1, PixivMode.all);
    }

    public void testBookmarkData(long pid) {
        //测试收藏数据
        bookmarkData(pid).async(new BaseCallback<BookmarkDataRes>() {
            @Override
            public BookmarkDataRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), BookmarkDataRes.class);
            }

            @Override
            public void onSuccess(BookmarkDataRes res) {
                final BookmarkData bookmarkData = res.getBody().getBookmarkData();
                if (bookmarkData != null) {
                    System.out.printf("[收藏数据] pid = %d 收藏id = %d\n", pid, bookmarkData.getId());
                } else {
                    System.out.println("未收藏 pid = " + pid);
                }
            }
        });
    }

    public void testDetail(long pid) {
        //测试绘画详情
        detail(pid).async(new BaseCallback<IllustMangaRes>() {
            @Override
            public IllustMangaRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), IllustMangaRes.class);
            }

            @Override
            public void onSuccess(IllustMangaRes res) {
                final ZonedDateTime createDate = res.getBody().getCreateDate();
                System.out.printf("[绘画详情] pid = %d 创建于 %s\n", pid, createDate);
            }
        });
    }

    public void testDiscovery(long pid) {
        discovery(new IllustsDiscoveryParam(pid)).async(new BaseCallback<DiscoveryRes>() {
            @Override
            public DiscoveryRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), DiscoveryRes.class);
            }

            @Override
            public void onSuccess(DiscoveryRes res) {
                final List<DiscoveryBody.RecommendedIllust> list = res.getBody().getRecommendedIllusts();
                System.out.printf("[发现绘画] pid = %d  %d 个\n", pid, list.size());
            }
        });
    }

    public void testLatest(int page, PixivMode mode) {
        latest(page, mode).async(new BaseCallback<IllustMangaFollowLatestRes>() {
            @Override
            public IllustMangaFollowLatestRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), IllustMangaFollowLatestRes.class);
            }

            @Override
            public void onSuccess(IllustMangaFollowLatestRes res) {
                final List<ArtworkInfo> illusts = res.getBody().getThumbnails().getIllust();
                System.out.printf("[最新关注] page = %d 模式 = %s %d 个\n", page, mode.name(), illusts.size());
            }
        });
    }

    public void testLike(long pid) {
        like(pid).async(new BaseCallback<LikeRes>() {
            @Override
            public LikeRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), LikeRes.class);
            }

            @Override
            public void onSuccess(LikeRes res) {
                System.out.printf("[喜欢绘画] pid = %d  %s\n", pid, res.getBody().getIsLiked());
            }
        });
    }

    public void testRecommendIllusts(long pid) {
        recommendIllusts(Collections.singletonList(pid)).async(new BaseCallback<IllustRecommendRes>() {
            @Override
            public IllustRecommendRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), IllustRecommendRes.class);
            }

            @Override
            public void onSuccess(IllustRecommendRes res) {
                final List<ArtworkInfo> illusts = res.getBody().getIllusts();
                System.out.printf("[查询推荐绘画2] pid = %d  %s 个\n", pid, illusts.size());
            }
        });
    }

    public void testRecommendInit(long pid, int limit) {
        recommendInit(pid, limit).async(new BaseCallback<IllustRecommendInitRes>() {
            @Override
            public IllustRecommendInitRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), IllustRecommendInitRes.class);
            }

            @Override
            public void onSuccess(IllustRecommendInitRes res) {
                final List<ArtworkInfo> illusts = res.getBody().getIllusts();
                System.out.printf("[查询推荐绘画] pid = %d  %s 个\n", pid, illusts.size());
            }
        });
    }

    public void testSearch(String keywords) {
        search(keywords, new IllustMangaSearchParam()).async(new BaseCallback<IllustMangaSearchRes>() {
            @Override
            public IllustMangaSearchRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), IllustMangaSearchRes.class);
            }

            @Override
            public void onSuccess(IllustMangaSearchRes res) {
                final List<ArtworkInfo> illusts = res.getBody().getIllustManga().getData();
                System.out.printf("[搜索绘画] 关键字 = %s  %s 个\n", keywords, illusts.size());
            }
        });
    }

    public void testUgoiraMeta(long pid) {
        ugoiraMeta(pid).async(new BaseCallback<UgoiraMetaRes>() {
            @Override
            public UgoiraMetaRes convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), UgoiraMetaRes.class);
            }

            @Override
            public void onSuccess(UgoiraMetaRes res) {
                final String originalSrc = res.getBody().getOriginalSrc();
                System.out.println("[查询动图] originalSrc = " + originalSrc);
            }
        });

    }

    /**
     * 查询动图的其他信息
     * @param pid 绘画id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.UgoiraMetaBody>>
     * @since 2022/10/15 16:52
     */
    public PixivRequest<UgoiraMetaRes> ugoiraMeta(long pid) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illust/%d/ugoira_meta", pid)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }
}
