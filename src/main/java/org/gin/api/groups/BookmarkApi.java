package org.gin.api.groups;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.params.bookmark.AddIllustMangaParam;
import org.gin.params.bookmark.AddNovelParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.BookmarkAddRes;
import org.gin.response.SimplePixivResponse;

import java.util.Collection;
import java.util.HashMap;

import static org.gin.request.PixivRequestBody.createFormBody;
import static org.gin.request.PixivRequestBody.createJsonBody;

/**
 * 收藏相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 10:01
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BookmarkApi {
    private final PixivApi api;

    /**
     * 收藏绘画
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.response.BookmarkAddRes>
     * @since 2022/11/17 10:07
     */
    public PixivRequest<BookmarkAddRes> addIllust(@NonNull AddIllustMangaParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illusts/bookmarks/add")
                .build();
        return new PixivRequest<>(url, api.getClient(), createJsonBody(param));
    }

    /**
     * 收藏小说
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 11:14
     */
    public PixivRequest<SimplePixivResponse> addNovel(@NonNull AddNovelParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novels/bookmarks/add")
                .build();
        return new PixivRequest<>(url, api.getClient(), createJsonBody(param));
    }

    /**
     * 删除绘画收藏
     * @param bookmarkId 收藏id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 10:12
     */
    public PixivRequest<SimplePixivResponse> delIllust(long bookmarkId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illusts/bookmarks/delete")
                .build();
        return new PixivRequest<>(url, api.getClient(), createFormBody("bookmark_id", bookmarkId));
    }

    /**
     * 删除绘画收藏(批量)
     * @param bookmarkIds 收藏id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 10:12
     */
    public PixivRequest<SimplePixivResponse> delIllusts(@NonNull Collection<Long> bookmarkIds) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/illusts/bookmarks/remove")
                .build();
        return new PixivRequest<>(url, api.getClient(), createJsonBody("bookmarkIds", bookmarkIds));
    }

    /**
     * 删除小说收藏
     * @param bookmarkId 收藏id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 11:14
     */
    public PixivRequest<SimplePixivResponse> delNovel(long bookmarkId) {
        final HashMap<String, Long> body = new HashMap<>(2);
        body.put("book_id", bookmarkId);
        body.put("del", 1L);

        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novels/bookmarks/delete")
                .build();
        return new PixivRequest<>(url, api.getClient(), createFormBody(body));
    }

    /**
     * 删除小说收藏(批量)
     * @param bookmarkIds 收藏id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 11:14
     */
    public PixivRequest<SimplePixivResponse> delNovels(Collection<Long> bookmarkIds) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/novels/bookmarks/remove")
                .build();
        return new PixivRequest<>(url, api.getClient(), createJsonBody("bookmarkIds", bookmarkIds));
    }
}