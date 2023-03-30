package org.gin.pixiv.api;

import lombok.RequiredArgsConstructor;
import org.gin.emuns.PixivRest;
import org.gin.params.user.BookmarksParam;
import org.gin.pixiv.call.PixivCallStandard;
import org.gin.pixiv.callback.StandardCallback;
import org.gin.pixiv.main.PixivClient;
import org.gin.pixiv.response.body.illust.IllustBodyUserBookmark;
import org.gin.pixiv.response.body.novel.NovelBodyUserBookmark;
import org.gin.response.body.comment.CommonBookmarkTagsBody;
import org.gin.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 用户收藏API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/30 10:26
 */
@RequiredArgsConstructor
public class UserBookmarkApi {
    private final PixivClient client;


    /**
     * 查询用户收藏的绘画中使用的标签
     * @param userId 用户id
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.response.body.comment.CommonBookmarkTagsBody>
     * @since 2023/3/30 10:41
     */
    public PixivCallStandard<CommonBookmarkTagsBody> getIllustTags(long userId) {
        return client.standard(String.format("/ajax/user/%d/illusts/bookmark/tags", userId), CommonBookmarkTagsBody.class);
    }

    /**
     * 查询用户收藏的绘画
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.illust.IllustBodyUserBookmark>
     * @since 2023/3/30 10:44
     */
    public PixivCallStandard<IllustBodyUserBookmark> getIllusts(long userId, @NotNull BookmarksParam param) {
        return client.standard(String.format("/ajax/user/%d/illusts/bookmarks", userId), IllustBodyUserBookmark.class, param);
    }

    /**
     * 查询用户收藏小说中使用的标签
     * @param userId 用户id
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.response.body.comment.CommonBookmarkTagsBody>
     * @since 2023/3/30 10:53
     */
    public PixivCallStandard<CommonBookmarkTagsBody> getNovelTags(long userId) {
        return client.standard(String.format("/ajax/user/%d/novels/bookmark/tags", userId), CommonBookmarkTagsBody.class);
    }

    /**
     * 查询用户收藏的小说
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.novel.NovelBodyUserBookmark>
     * @since 2023/3/30 10:53
     */
    public PixivCallStandard<NovelBodyUserBookmark> getNovels(long userId, @NotNull BookmarksParam param) {
        return client.standard(String.format("/ajax/user/%d/novels/bookmarks", userId), NovelBodyUserBookmark.class, param);
    }

    public void zTest() {
        long uid = 57680761;
        final BookmarksParam param = new BookmarksParam(1, 10, PixivRest.show).untagged();

        zTestIllust(uid, param);
        zTestNovel(uid, param);

    }

    private void zTestIllust(long uid, BookmarksParam param) {
        getIllusts(uid, param).async(new StandardCallback<IllustBodyUserBookmark>() {
            @Override
            public void onSuccess(IllustBodyUserBookmark body) {
                JsonUtils.printJson(body);
            }
        });
        getIllustTags(uid).async(new StandardCallback<CommonBookmarkTagsBody>() {
            @Override
            public void onSuccess(CommonBookmarkTagsBody body) {
                System.out.printf("[用户收藏绘画标签] uid: %d 标签数 %s \n", uid, body.getPublicTag().size());

            }
        });
    }

    private void zTestNovel(long uid, BookmarksParam param) {
        getNovels(uid, param).async(new StandardCallback<NovelBodyUserBookmark>() {
            @Override
            public void onSuccess(NovelBodyUserBookmark body) {
                JsonUtils.printJson(body);
            }
        });

        getNovelTags(uid).async(new StandardCallback<CommonBookmarkTagsBody>() {
            @Override
            public void onSuccess(CommonBookmarkTagsBody body) {
                System.out.printf("[用户收藏小说标签] uid: %d 标签数 %s \n", uid, body.getPublicTag().size());

            }
        });
    }

}   
