package org.gin.api.groups;

import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.OkHttpClient;
import org.gin.api.PixivApi;
import org.gin.params.SimpleParam;
import org.gin.params.user.BookmarksParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.gin.response.body.comment.CommonBookmarkTagsBody;
import org.gin.response.body.illustmanga.IllustMangaBookmarksBody;
import org.gin.response.body.novel.NovelBookmarksBody;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static org.gin.request.PixivUrl.createHttpUrl;

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
     * 查询用户收藏的绘画
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustsBookmarksBody>>
     * @since 2022/10/15 12:19
     */
    public static PixivRequest<PixivResponse<IllustMangaBookmarksBody>> illustsBookmarks(long userId,
                                                                                         @NotNull BookmarksParam param,
                                                                                         @NotNull PixivCookieToken pixivCookieToken,
                                                                                         @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/illusts/bookmarks", userId)
                , client);
    }

    /**
     * 查询用户收藏的绘画中使用的标签
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.IllustsBookmarksBody>>
     * @since 2022/10/15 12:29
     */
    public static PixivRequest<PixivResponse<CommonBookmarkTagsBody>> illustsBookmarkTags(long userId,
                                                                                          @NotNull SimpleParam param,
                                                                                          @NotNull PixivCookieToken pixivCookieToken,
                                                                                          @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/illusts/bookmark/tags", userId)
                , client);
    }

    /**
     * 查询用户收藏的小说
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.novel.NovelBookmarksBody>>
     * @since 2022/10/20 9:01
     */
    public static PixivRequest<PixivResponse<NovelBookmarksBody>> novelsBookmarks(long userId,
                                                                                  @NotNull BookmarksParam param,
                                                                                  @NotNull PixivCookieToken pixivCookieToken,
                                                                                  @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/novels/bookmarks", userId)
                , client);
    }

    /**
     * 查询用户收藏小说中使用的标签
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.comment.CommonBookmarkTagsBody>>
     * @since 2022/10/17 13:34
     */

    public static PixivRequest<PixivResponse<CommonBookmarkTagsBody>> novelsBookmarkTags(long userId,
                                                                                         @NotNull SimpleParam param,
                                                                                         @NotNull PixivCookieToken pixivCookieToken,
                                                                                         @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/novels/bookmark/tags", userId)
                , client);
    }
}   
