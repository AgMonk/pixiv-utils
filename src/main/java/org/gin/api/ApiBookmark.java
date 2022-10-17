package org.gin.api;

import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.MapUtils;
import org.gin.params.SimpleParam;
import org.gin.params.bookmark.AddIllustMangaParam;
import org.gin.params.bookmark.AddNovelParam;
import org.gin.request.*;
import org.gin.response.PixivResponse;
import org.gin.response.body.BookmarkAddBody;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;

/**
 * 收藏相关接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 14:04
 */
public class ApiBookmark {
    /**
     * 添加收藏
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.BookmarkAddBody>>
     * @since 2022/10/15 14:15
     */
    public static PixivRequest<PixivResponse<BookmarkAddBody>> addIllust(@NonNull AddIllustMangaParam param,
                                                                         @NonNull PixivCookieToken pixivCookieToken,
                                                                         @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(Pixiv.DOMAIN + "/ajax/illusts/bookmarks/add")
                , PixivRequestBody.createJsonBody(param)
                , client, pixivCookieToken);
    }

    /**
     * 删除收藏
     * @param param            参数
     * @param bookmarkId       收藏id
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
     * @since 2022/10/15 14:33
     */
    public static PixivRequest<PixivResponse<String>> delIllust(long bookmarkId,
                                                                @NotNull SimpleParam param,
                                                                @NonNull PixivCookieToken pixivCookieToken,
                                                                @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/illusts/bookmarks/delete")
                , PixivRequestBody.createFormBody("bookmark_id", bookmarkId)
                , client, pixivCookieToken);
    }

    /**
     * 删除收藏(批量)
     * @param param            参数
     * @param bookmarkIds      收藏id
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
     * @since 2022/10/15 14:33
     */
    public static PixivRequest<PixivResponse<String>> delIllust(Collection<Long> bookmarkIds,
                                                                @NotNull SimpleParam param,
                                                                @NonNull PixivCookieToken pixivCookieToken,
                                                                @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/illusts/bookmarks/remove")
                , PixivRequestBody.createJsonBody("bookmarkIds", bookmarkIds)
                , client, pixivCookieToken);
    }

    /**
     * 收藏小说
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.Long>>
     * @since 2022/10/17 11:52
     */
    public static PixivRequest<PixivResponse<Long>> addNovel(@NonNull AddNovelParam param,
                                                             @NonNull PixivCookieToken pixivCookieToken,
                                                             @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(Pixiv.DOMAIN + "/ajax/novels/bookmarks/add")
                , PixivRequestBody.createJsonBody(param)
                , client, pixivCookieToken);
    }

    /**
     * 删除收藏小说
     * @param bookmarkId       收藏id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
     * @since 2022/10/17 13:20
     */
    public static PixivRequest<PixivResponse<String>> delNovel(long bookmarkId,
                                                               @NotNull SimpleParam param,
                                                               @NonNull PixivCookieToken pixivCookieToken,
                                                               @NonNull OkHttpClient client
    ) {
        final HashMap<String, Long> body = MapUtils.singleTon("book_id", bookmarkId);
        body.put("del", 1L);
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/novels/bookmarks/delete")
                , PixivRequestBody.createFormBody(body)
                , client, pixivCookieToken);
    }
}
