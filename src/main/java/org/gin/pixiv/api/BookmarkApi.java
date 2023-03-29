package org.gin.pixiv.api;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.gin.params.bookmark.AddIllustMangaParam;
import org.gin.params.bookmark.AddNovelParam;
import org.gin.pixiv.call.PixivCallStandard;
import org.gin.pixiv.enums.ParamType;
import org.gin.pixiv.main.PixivClient;
import org.gin.response.body.BookmarkAddBody;
import org.gin.utils.MapUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * 收藏相关API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 11:12
 */
@RequiredArgsConstructor
public class BookmarkApi {
    private final PixivClient client;

    /**
     * 收藏绘画
     * @param param 参数
     * @return {@link PixivCallStandard<BookmarkAddBody>}
     * @since 2023/3/29 11:19
     */
    public PixivCallStandard<BookmarkAddBody> postAddIllust(@NonNull AddIllustMangaParam param) {
        return client.standard(ParamType.JSON, "/ajax/illusts/bookmarks/add", param, BookmarkAddBody.class);
    }

    /**
     * 收藏小说
     * @param param 参数
     * @return {@link PixivCallStandard<String>}
     * @since 2023/3/29 11:21
     */
    public PixivCallStandard<String> postAddNovel(@NonNull AddNovelParam param) {
        return client.standard(ParamType.JSON, "/ajax/novels/bookmarks/add", param, String.class);
    }

    /**
     * 删除绘画收藏
     * @param bookmarkId 收藏id
     * @return {@link PixivCallStandard<String>}
     * @since 2023/3/29 11:35
     */
    public PixivCallStandard<String> postDelIllust(long bookmarkId) {
        return client.standard(ParamType.FORM, "/ajax/illusts/bookmarks/delete", MapUtils.singleEntry("bookmark_id", bookmarkId), String.class);
    }

    /**
     * 删除绘画收藏(批量)
     * @param bookmarkIds 收藏id
     * @return {@link PixivCallStandard<String>}
     * @since 2023/3/29 11:36
     */
    public PixivCallStandard<String> postDelIllusts(@NonNull Collection<Long> bookmarkIds) {
        return client.standard(ParamType.JSON, "/ajax/illusts/bookmarks/remove", MapUtils.singleEntry("bookmarkIds", bookmarkIds), String.class);
    }

    /**
     * 删除小说收藏
     * @param bookmarkId 收藏id
     * @return {@link PixivCallStandard<String>}
     * @since 2023/3/29 11:36
     */
    public PixivCallStandard<String> postDelNovel(long bookmarkId) {
        final HashMap<String, Long> param = new HashMap<>(2);
        param.put("book_id", bookmarkId);
        param.put("del", 1L);

        return client.standard(ParamType.FORM, "/ajax/novels/bookmarks/delete", param, String.class);
    }

    /**
     * 删除小说收藏(批量)
     * @param bookmarkIds 收藏id
     * @return {@link PixivCallStandard<String>}
     * @since 2023/3/29 11:36
     */
    public PixivCallStandard<String> postDelNovels(Collection<Long> bookmarkIds) {
        return client.standard(ParamType.JSON, "/ajax/novels/bookmarks/remove", MapUtils.singleEntry("bookmarkIds", bookmarkIds), String.class);
    }

    public void zTest() throws IOException {
        final long pid = 99147997;
        final long nid = 17718240;

        zTestIllust(pid);
        zTestNovel(nid);
    }

    private void zTestIllust(long pid) throws IOException {
        final IllustApi illustApi = new IllustApi(client);
        final Long bookmarkId = illustApi.getDetail(pid).sync().getBookmarkData().getId();

        postDelIllust(bookmarkId).sync();
        final BookmarkAddBody bookmarkAddBody = postAddIllust(new AddIllustMangaParam(pid)).sync();
        final Long lastBookmarkId = bookmarkAddBody.getLastBookmarkId();
        postDelIllusts(Collections.singleton(lastBookmarkId)).sync();
        postAddIllust(new AddIllustMangaParam(pid)).sync();
    }

    private void zTestNovel(long nid) throws IOException {
        //todo
//        final Long bookmarkId = api.getNovelApi().detail(nid).sync().getBody().getBookmarkData().getId();
//        delNovel(bookmarkId).sync();
//        final SimplePixivResponse response = postAddNovel(new AddNovelParam(nid)).sync();
//        delNovels(Collections.singleton(Long.parseLong(response.getBody()))).sync();
//        postAddNovel(new AddNovelParam(nid)).sync();
    }

}   
