package org.gin.pixiv.api;

import lombok.RequiredArgsConstructor;
import org.gin.emuns.PixivMode;
import org.gin.params.illustmanga.IllustMangaSearchParam;
import org.gin.params.illustmanga.IllustsDiscoveryParam;
import org.gin.pixiv.call.PixivCallStandard;
import org.gin.pixiv.callback.StandardCallback;
import org.gin.pixiv.enums.IllustSearchType;
import org.gin.pixiv.enums.ParamType;
import org.gin.pixiv.main.PixivClient;
import org.gin.pixiv.response.body.bookmark.BookmarkDataBody;
import org.gin.pixiv.response.body.common.DiscoveryBody;
import org.gin.pixiv.response.body.common.FollowLatestBody;
import org.gin.pixiv.response.body.common.LikeBody;
import org.gin.pixiv.response.body.illust.*;
import org.gin.utils.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 绘画API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 15:52
 **/
@SuppressWarnings("SameParameterValue")
@RequiredArgsConstructor
public class IllustApi {
    private final PixivClient client;

    /**
     * 查询绘画的收藏状态
     * @param pid 绘画id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.BookmarkDataBody>>
     * @since 2022/10/15 17:19
     */
    public PixivCallStandard<BookmarkDataBody> getBookmarkData(long pid) {
        return client.standard(String.format("/ajax/illust/%d/bookmarkData", pid), BookmarkDataBody.class);
    }

    /**
     * 查询绘画的详情信息
     * @param pid 绘画id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.ArtworkBody>>
     * @since 2022/10/14 17:07
     */
    public PixivCallStandard<IllustBodyDetail> getDetail(long pid) {
        return client.standard(String.format("/ajax/illust/%d", pid), IllustBodyDetail.class);
    }

    /**
     * 发现绘画
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.pixiv.response.body.common.DiscoveryBody>>
     * @since 2022/10/21 9:14
     */
    public PixivCallStandard<DiscoveryBody> getDiscovery(@NotNull IllustsDiscoveryParam param) {
        return client.standard("/ajax/discovery/artworks", DiscoveryBody.class, param);
    }

    /**
     * 查询关注作者的最新绘画
     * @param page 页码
     * @param mode 模式 可选值: all r18
     * @return org.gin.request.PixivRequest<org.gin.response.body.illustmanga.IllustMangaFollowLatestRes>
     * @since 2022/11/16 14:11
     */
    public PixivCallStandard<FollowLatestBody> getLatest(int page, @NotNull PixivMode mode) {
        final HashMap<String, Object> param = new HashMap<>(2);
        param.put("page", page);
        param.put("mod", mode.name());
        return client.standard("/ajax/follow_latest/illust", FollowLatestBody.class, param);
    }

    /**
     * 查询推荐绘画2
     * @param ids 基准id，可以直接使用 recommendInit 方法的返回结果
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.pixiv.response.body.common.DiscoveryBody>>
     * @since 2022/11/1 9:48
     */
    public PixivCallStandard<IllustBodyRecommend> getRecommendIllusts(@NotNull List<Long> ids) {
        return client.standard("/ajax/illust/recommend/illusts", IllustBodyRecommend.class, MapUtils.singleEntry("illust_ids[]", ids));
    }

    /**
     * 查询推荐绘画
     * @param pid   基准绘画pid
     * @param limit 绘画数量
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.pixiv.response.body.common.DiscoveryBody>>
     * @since 2022/11/1 9:43
     */
    public PixivCallStandard<IllustBodyRecommendInit> getRecommendInit(long pid, int limit) {
        return client.standard(String.format("/ajax/illust/%d/recommend/init", pid),
                               IllustBodyRecommendInit.class, MapUtils.singleEntry("limit", limit)
        );
    }

    /**
     * 搜索
     * @param keywords 关键字
     * @param param    参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.SearchBody>>
     * @since 2022/10/14 17:28
     */
    public PixivCallStandard<IllustBodySearch> getSearch(@NotNull String keywords, @NotNull IllustMangaSearchParam param) {
        return client.standard("/ajax/search/illustrations/" + keywords, IllustBodySearch.class, param);
    }

    /**
     * 查询动图的其他信息
     * @param pid 绘画id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.UgoiraMetaBody>>
     * @since 2022/10/15 16:52
     */
    public PixivCallStandard<UgoiraMetaBody> getUgoiraMeta(long pid) {
        return client.standard(String.format("/ajax/illust/%d/ugoira_meta", pid), UgoiraMetaBody.class);
    }

    /**
     * 喜欢绘画
     * @param pid 绘画id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
     * @since 2022/10/15 16:06
     */
    public PixivCallStandard<LikeBody> postLike(long pid) {
        return client.standard(ParamType.JSON, "/ajax/illusts/like", LikeBody.class, MapUtils.singleEntry("illust_id", pid));
    }

    public void zTest() {
        long pid = 99147997L;
        long ugoiraPid = 102814610;

        zTestBookmarkData(pid);
        zTestDetail(pid);
        zTestDiscovery(pid);
        zTestLike(pid);
        zTestRecommendIllusts(pid);
        zTestRecommendInit(pid, 20);
        zTestSearch("RO635");
        zTestUgoiraMeta(ugoiraPid);
        zTestLatest(1, PixivMode.all);
    }

    private void zTestBookmarkData(long pid) {
        //测试收藏数据
        getBookmarkData(pid).async(new StandardCallback<BookmarkDataBody>() {
            @Override
            public void onSuccess(BookmarkDataBody body) {
                if (body != null) {
                    System.out.printf("[收藏数据] pid = %d 收藏id = %d\n", pid, body.getId());
                } else {
                    System.out.println("未收藏 pid = " + pid);
                }
            }
        });
    }

    private void zTestDetail(long pid) {
        //测试绘画详情
        getDetail(pid).async(new StandardCallback<IllustBodyDetail>() {
            @Override
            public void onSuccess(IllustBodyDetail body) {
                System.out.printf("[绘画详情] pid = %d 创建于 %s\n", pid, body.getCreateDate());
            }
        });
    }

    private void zTestDiscovery(long pid) {
        getDiscovery(new IllustsDiscoveryParam(pid)).async(new StandardCallback<DiscoveryBody>() {
            @Override
            public void onSuccess(DiscoveryBody body) {
                System.out.printf("[发现绘画] pid = %d  %d 个\n", pid, body.getThumbnails().getIllust().size());

            }
        });
    }

    private void zTestLatest(int page, PixivMode mode) {
        getLatest(page, mode).async(new StandardCallback<FollowLatestBody>() {
            @Override
            public void onSuccess(FollowLatestBody body) {
                System.out.printf("[最新关注] page = %d 模式 = %s %d 个\n", page, mode.name(), body.getThumbnails().getIllust().size());
            }
        });
    }

    private void zTestLike(long pid) {
        postLike(pid).async(new StandardCallback<LikeBody>() {
            @Override
            public void onSuccess(LikeBody body) {
                System.out.printf("[喜欢绘画] pid = %d  %s\n", pid, body.getIsLiked());
            }
        });
    }

    private void zTestRecommendIllusts(long pid) {
        getRecommendIllusts(Collections.singletonList(pid)).async(new StandardCallback<IllustBodyRecommend>() {
            @Override
            public void onSuccess(IllustBodyRecommend body) {
                System.out.printf("[查询推荐绘画2] pid = %d  %s 个\n", pid, body.getIllusts().size());

            }
        });
    }

    private void zTestRecommendInit(long pid, int limit) {
        getRecommendInit(pid, limit).async(new StandardCallback<IllustBodyRecommendInit>() {
            @Override
            public void onSuccess(IllustBodyRecommendInit body) {
                System.out.printf("[查询推荐绘画] pid = %d  %s 个\n", pid, body.getIllusts().size());
            }
        });
    }

    private void zTestSearch(String keywords) {
        final IllustMangaSearchParam param = new IllustMangaSearchParam();
        param.setType(IllustSearchType.illust_and_ugoira);
        getSearch(keywords, param).async(new StandardCallback<IllustBodySearch>() {
            @Override
            public void onSuccess(IllustBodySearch body) {
                System.out.printf("[搜索绘画] 关键字 = %s  %s 个\n", keywords, body.getIllustManga().getData().size());

            }
        });
    }

    private void zTestUgoiraMeta(long pid) {
        getUgoiraMeta(pid).async(new StandardCallback<UgoiraMetaBody>() {
            @Override
            public void onSuccess(UgoiraMetaBody body) {
                System.out.println("[查询动图] originalSrc = " + body.getOriginalSrc());

            }
        });
    }
}
