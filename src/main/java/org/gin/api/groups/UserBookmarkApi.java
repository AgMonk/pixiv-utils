package org.gin.api.groups;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import org.gin.api.PixivApi;
import org.gin.emuns.PixivRest;
import org.gin.params.user.BookmarksParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.comment.CommonBookmarkTagsBody;
import org.gin.response.body.comment.CommonBookmarkTagsRes;
import org.gin.response.body.illustmanga.IllustMangaBookmarksBody;
import org.gin.response.body.illustmanga.IllustMangaBookmarksRes;
import org.gin.response.body.novel.NovelBookmarksBody;
import org.gin.response.body.novel.NovelBookmarksRes;
import org.gin.response.callback.BaseCallback;
import org.gin.response.convertor.Convertor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 用户收藏API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 16:51
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UserBookmarkApi {
    private final PixivApi api;


    /**
     * 查询用户收藏的绘画中使用的标签
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustsBookmarksBody>>
     * @since 2022/10/15 12:29
     */
    public PixivRequest<CommonBookmarkTagsRes> illustTags(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/illusts/bookmark/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, CommonBookmarkTagsRes.class));
    }

    /**
     * 查询用户收藏的绘画
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustsBookmarksBody>>
     * @since 2022/10/15 12:19
     */
    public PixivRequest<IllustMangaBookmarksRes> illusts(long userId, @NotNull BookmarksParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/illusts/bookmarks", userId)
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, IllustMangaBookmarksRes.class));
    }

    /**
     * 查询用户收藏小说中使用的标签
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.comment.CommonBookmarkTagsBody>>
     * @since 2022/10/17 13:34
     */

    public PixivRequest<CommonBookmarkTagsRes> novelTags(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/novels/bookmark/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, CommonBookmarkTagsRes.class));
    }

    /**
     * 查询用户收藏的小说
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.novel.NovelBookmarksBody>>
     * @since 2022/10/20 9:01
     */
    public PixivRequest<NovelBookmarksRes> novels(long userId, @NotNull BookmarksParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/novels/bookmarks", userId)
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, NovelBookmarksRes.class));
    }

    public void zTest() {
        long authorId = 17113161;
        long uid = 57680761;
        final BookmarksParam param = new BookmarksParam(1, 10, PixivRest.show).untagged();

        zTestIllust(uid, param);
        zTestNovel(uid, param);

    }

    private void zTestIllust(long uid, BookmarksParam param) {
        illusts(uid, param).async(new BaseCallback<PixivResponse<IllustMangaBookmarksBody>>() {
            @Override
            public PixivResponse<IllustMangaBookmarksBody> convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), new TypeReference<PixivResponse<IllustMangaBookmarksBody>>() {
                });
            }

            @Override
            public void onSuccess(PixivResponse<IllustMangaBookmarksBody> res) {
                final long total = res.getBody().getTotal();
                System.out.printf("[用户绘画] uid: %d 作品数: %d \n", uid, total);
            }
        });
        illustTags(uid).async(new BaseCallback<PixivResponse<CommonBookmarkTagsBody>>() {
            @Override
            public PixivResponse<CommonBookmarkTagsBody> convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), new TypeReference<PixivResponse<CommonBookmarkTagsBody>>() {
                });
            }

            @Override
            public void onSuccess(PixivResponse<CommonBookmarkTagsBody> res) {
                final int size = res.getBody().getPublicTag().size();
                System.out.printf("[用户绘画标签] uid: %d 标签数 %s \n", uid, size);
            }
        });
    }

    private void zTestNovel(long uid, BookmarksParam param) {
        novels(uid, param).async(new BaseCallback<PixivResponse<NovelBookmarksBody>>() {
            @Override
            public PixivResponse<NovelBookmarksBody> convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), new TypeReference<PixivResponse<NovelBookmarksBody>>() {
                });
            }

            @Override
            public void onSuccess(PixivResponse<NovelBookmarksBody> res) {
                final Long total = res.getBody().getTotal();
                System.out.printf("[用户小说] uid: %d 作品数: %d \n", uid, total);
            }
        });

        novelTags(uid).async(new BaseCallback<PixivResponse<CommonBookmarkTagsBody>>() {
            @Override
            public PixivResponse<CommonBookmarkTagsBody> convert(ResponseBody responseBody) throws IOException {
                return JSONObject.parseObject(responseBody.string(), new TypeReference<PixivResponse<CommonBookmarkTagsBody>>() {
                });
            }

            @Override
            public void onSuccess(PixivResponse<CommonBookmarkTagsBody> res) {
                final int size = res.getBody().getPublicTag().size();
                System.out.printf("[用户小说标签] uid: %d 标签数 %s \n", uid, size);
            }
        });
    }
}   
