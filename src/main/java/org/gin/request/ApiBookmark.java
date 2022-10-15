package org.gin.request;

import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.params.BookmarksAddParam;
import org.gin.response.PixivResponse;
import org.gin.response.body.BookmarkAddBody;

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
    public static PixivRequest<PixivResponse<BookmarkAddBody>> addIllust(@NonNull BookmarksAddParam param,
                                                                         @NonNull PixivCookieToken pixivCookieToken,
                                                                         @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivCommon.createHttpUrl(PixivUrl.DOMAIN + "/ajax/illusts/bookmarks/add")
                , PixivCommon.createJsonBody(param)
                , client, pixivCookieToken);
    }
/**
 * 删除收藏
 * @param bookmarkId       收藏id
 * @param pixivCookieToken cooke和token
 * @param client           客户端
 * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < java.lang.String>>
 * @since 2022/10/15 14:33
 */
    public static PixivRequest<PixivResponse<String>> delIllust(long bookmarkId,
                                                                @NonNull PixivCookieToken pixivCookieToken,
                                                                @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(
                PixivCommon.createHttpUrl(PixivUrl.DOMAIN + "/ajax/illusts/bookmarks/delete")
                , PixivCommon.createFormBody("bookmark_id", bookmarkId)
                , client, pixivCookieToken);
    }
}
