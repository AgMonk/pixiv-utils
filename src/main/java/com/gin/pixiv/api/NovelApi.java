package com.gin.pixiv.api;

import com.gin.pixiv.call.PixivCallStandard;
import com.gin.pixiv.callback.StandardCallback;
import com.gin.pixiv.enums.PixivMode;
import com.gin.pixiv.main.PixivClient;
import com.gin.pixiv.params.LatestParam;
import com.gin.pixiv.params.illustmanga.NovelsDiscoveryParam;
import com.gin.pixiv.params.novel.NovelSearchParam;
import com.gin.pixiv.response.body.bookmark.BookmarkDataBody;
import com.gin.pixiv.response.body.common.DiscoveryBody;
import com.gin.pixiv.response.body.common.FollowLatestBody;
import com.gin.pixiv.response.body.novel.NovelBodyDetail;
import com.gin.pixiv.response.body.novel.NovelBodySearch;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * 小说API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 11:49
 */
@RequiredArgsConstructor
public class NovelApi {
    private final PixivClient client;

    /**
     * 查询小说的收藏状态
     * @param nid 小说id
     * @return org.gin.request.PixivRequest<org.gin.response.body.BookmarkDataRes>
     * @since 2022/11/16 14:59
     */
    public PixivCallStandard<BookmarkDataBody> getBookmarkData(long nid) {
        return client.standard(String.format("/ajax/novel/%d/bookmarkData", nid), BookmarkDataBody.class);
    }

    /**
     * 查询小说详情
     * @param nid 小说id
     * @return org.gin.request.PixivRequest<NovelDetailRes>
     * @since 2022/11/16 15:00
     */
    public PixivCallStandard<NovelBodyDetail> getDetail(long nid) {
        return client.standard("/ajax/novel/" + nid, NovelBodyDetail.class);
    }

    /**
     * 发现
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.pixiv.response.PixivResponse < org.gin.pixiv.response.body.common.DiscoveryBody>>
     * @since 2022/10/21 9:14
     */
    public PixivCallStandard<DiscoveryBody> getDiscovery(@NotNull NovelsDiscoveryParam param) {
        return client.standard("/ajax/discovery/novels", DiscoveryBody.class, param);
    }

    /**
     * 查询关注作者的最新小说
     * @param param 参数
     * @return {@link PixivCallStandard<FollowLatestBody>}
     * @since 2023/3/29 13:13
     */
    public PixivCallStandard<FollowLatestBody> getLatest(@NotNull LatestParam param) {
        return client.standard("/ajax/follow_latest/novel", FollowLatestBody.class, param);
    }

    /**
     * 搜索
     * @param keywords 关键字
     * @param param    参数
     * @return org.gin.request.PixivRequest<org.gin.pixiv.response.PixivResponse < org.gin.response.body.novel.NovelSearchBody>>
     * @since 2022/11/16 15:00
     */
    public PixivCallStandard<NovelBodySearch> getSearch(@NotNull String keywords, @NotNull NovelSearchParam param) {
        return client.standard("/ajax/search/novels/" + keywords, NovelBodySearch.class, param);
    }

    public void zTest() {
        long nid = 15809265;
        zTestLatest(new LatestParam(1, PixivMode.all));
        zTestBookmarkData(nid);
        zTestDetail(nid);
        zTestDiscovery(nid);
        zTestSearch("RO635");
    }

    void zTestLatest(LatestParam param) {
        getLatest(param).async(new StandardCallback<FollowLatestBody>() {
            @Override
            public void onSuccess(FollowLatestBody body) {
                System.out.printf("[最新小说] page: %d 结果 %d 个 \n", param.getPage(), body.getThumbnails().getNovel().size());
            }
        });
    }

    private void zTestBookmarkData(long nid) {
        getBookmarkData(nid).async(new StandardCallback<BookmarkDataBody>() {
            @Override
            public void onSuccess(BookmarkDataBody body) {
                if (body != null) {
                    System.out.printf("[收藏数据] nid = %d 收藏id = %d\n", nid, body.getId());
                } else {
                    System.out.println("未收藏 nid = " + nid);
                }
            }
        });
    }

    private void zTestDetail(long nid) {
        getDetail(nid).async(new StandardCallback<NovelBodyDetail>() {
            @Override
            public void onSuccess(NovelBodyDetail body) {
                System.out.printf("[小说详情] nid: %d 创建于 %s \n", nid, body.getCreateDate());
            }
        });
    }

    private void zTestDiscovery(long nid) {
        getDiscovery(new NovelsDiscoveryParam(nid)).async(new StandardCallback<DiscoveryBody>() {
            @Override
            public void onSuccess(DiscoveryBody body) {
                System.out.printf("[发现小说] nid: %d 结果 %d 个 \n", nid, body.getThumbnails().getNovel().size());
            }
        });
    }

    private void zTestSearch(String keywords) {
        getSearch(keywords, new NovelSearchParam()).async(new StandardCallback<NovelBodySearch>() {
            @Override
            public void onSuccess(NovelBodySearch body) {
                System.out.printf("[搜索小说] 关键字: %s 结果 %d 个 \n", keywords, body.getNovel().getData().size());
            }
        });
    }

}   
