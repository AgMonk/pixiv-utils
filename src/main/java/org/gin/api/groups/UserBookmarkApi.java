package org.gin.api.groups;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.params.user.BookmarksParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.comment.CommonBookmarkTagsBody;
import org.gin.response.body.illustmanga.IllustMangaBookmarksBody;
import org.gin.response.body.novel.NovelBookmarksBody;
import org.jetbrains.annotations.NotNull;

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


    //todo

    /**
     * 查询用户收藏的绘画中使用的标签
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustsBookmarksBody>>
     * @since 2022/10/15 12:29
     */
    public PixivRequest<PixivResponse<CommonBookmarkTagsBody>> illustTags(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/illusts/bookmark/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

    /**
     * 查询用户收藏的绘画
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustsBookmarksBody>>
     * @since 2022/10/15 12:19
     */
    public PixivRequest<PixivResponse<IllustMangaBookmarksBody>> illusts(long userId, @NotNull BookmarksParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/illusts/bookmarks", userId)
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

    /**
     * 查询用户收藏小说中使用的标签
     * @param userId           用户id
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.comment.CommonBookmarkTagsBody>>
     * @since 2022/10/17 13:34
     */

    public PixivRequest<PixivResponse<CommonBookmarkTagsBody>> novelTags(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/novels/bookmark/tags", userId)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }

    /**
     * 查询用户收藏的小说
     * @param userId           用户id
     * @param param            参数
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.novel.NovelBookmarksBody>>
     * @since 2022/10/20 9:01
     */
    public PixivRequest<PixivResponse<NovelBookmarksBody>> novels(long userId, @NotNull BookmarksParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/novels/bookmarks", userId)
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient());
    }
}   
