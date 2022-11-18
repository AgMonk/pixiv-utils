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
import org.gin.request.PixivUrlBuilder;
import org.gin.response.SimplePixivResponse;
import org.gin.response.body.BookmarkAddRes;
import org.gin.response.convertor.Convertor;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import static org.gin.request.PixivRequestBody.createFormBody;
import static org.gin.request.PixivRequestBody.createJsonBody;

/**
 * 收藏相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 10:01
 */
@SuppressWarnings("SameParameterValue")
@Getter
@Setter
@RequiredArgsConstructor
public class BookmarkApi {
    private final PixivApi api;

    /**
     * 收藏绘画
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.response.body.BookmarkAddRes>
     * @since 2022/11/17 10:07
     */
    public PixivRequest<BookmarkAddRes> addIllust(@NonNull AddIllustMangaParam param) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/illusts/bookmarks/add")
                .build();
        return new PixivRequest<>(url, api.getClient(), body -> Convertor.common(body, BookmarkAddRes.class), createJsonBody(param));
    }

    /**
     * 收藏小说
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 11:14
     */
    public PixivRequest<SimplePixivResponse> addNovel(@NonNull AddNovelParam param) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/novels/bookmarks/add")
                .build();
        return new PixivRequest<>(url, api.getClient(), Convertor::simple, createJsonBody(param));
    }

    /**
     * 删除绘画收藏
     * @param bookmarkId 收藏id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 10:12
     */
    public PixivRequest<SimplePixivResponse> delIllust(long bookmarkId) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/illusts/bookmarks/delete")
                .build();
        return new PixivRequest<>(url, api.getClient(), Convertor::simple, createFormBody("bookmark_id", bookmarkId));
    }

    /**
     * 删除绘画收藏(批量)
     * @param bookmarkIds 收藏id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 10:12
     */
    public PixivRequest<SimplePixivResponse> delIllusts(@NonNull Collection<Long> bookmarkIds) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/illusts/bookmarks/remove")
                .build();
        return new PixivRequest<>(url, api.getClient(), Convertor::simple, createJsonBody("bookmarkIds", bookmarkIds));
    }

    /**
     * 删除小说收藏
     * @param bookmarkId 收藏id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 11:14
     */
    public PixivRequest<SimplePixivResponse> delNovel(long bookmarkId) {
        final HashMap<String, Long> param = new HashMap<>(2);
        param.put("book_id", bookmarkId);
        param.put("del", 1L);

        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/novels/bookmarks/delete")
                .build();
        return new PixivRequest<>(url, api.getClient(), Convertor::simple, createFormBody(param));
    }

    /**
     * 删除小说收藏(批量)
     * @param bookmarkIds 收藏id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 11:14
     */
    public PixivRequest<SimplePixivResponse> delNovels(Collection<Long> bookmarkIds) {
        final HttpUrl url = new PixivUrlBuilder()
                .setUrl(api.getDomain() + "/ajax/novels/bookmarks/remove")
                .build();
        return new PixivRequest<>(url, api.getClient(), Convertor::simple, createJsonBody("bookmarkIds", bookmarkIds));
    }

    public void zTest() throws IOException {
        final long pid = 99147997;
        final long nid = 17718240;

        zTestIllust(pid);
        zTestNovel(nid);
    }

    private void zTestIllust(long pid) throws IOException {
        final Long bookmarkId = api.getIllustApi().detail(pid).sync().getBody().getBookmarkData().getId();

        delIllust(bookmarkId).sync();
        final BookmarkAddRes bookmarkAddRes = addIllust(new AddIllustMangaParam(pid))
                .sync();
        final Long lastBookmarkId = bookmarkAddRes.getBody().getLastBookmarkId();
        delIllusts(Collections.singleton(lastBookmarkId)).sync();
        addIllust(new AddIllustMangaParam(pid))
                .sync();
    }

    private void zTestNovel(long nid) throws IOException {
        final Long bookmarkId = api.getNovelApi().detail(nid).sync().getBody().getBookmarkData().getId();
        delNovel(bookmarkId).sync();
        final SimplePixivResponse response = addNovel(new AddNovelParam(nid)).sync();
        delNovels(Collections.singleton(Long.parseLong(response.getBody()))).sync();
        addNovel(new AddNovelParam(nid)).sync();
    }
}
