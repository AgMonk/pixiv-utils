package org.gin.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.OkHttpClient;
import org.gin.params.SimpleParam;
import org.gin.params.user.BookmarksParam;
import org.gin.params.user.ProfileIllustsParam;
import org.gin.params.user.ProfileNovelsParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.PixivResponse;
import org.gin.response.body.illustmanga.CommonBookmarkTagsBody;
import org.gin.response.body.illustmanga.IllustMangaBookmarksBody;
import org.gin.response.body.novel.NovelBookmarksBody;
import org.gin.response.body.tag.UserTag;
import org.gin.response.body.user.CommissionRequestSentBody;
import org.gin.response.body.user.ProfileIllustsBody;
import org.gin.response.body.user.ProfileNovelsBody;
import org.gin.response.body.user.ProfileRealBody;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static org.gin.request.PixivUrl.createHttpUrl;

/**
 * 用户相关接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 17:39
 **/
public class ApiUser {



    /**
     * 用户的作品相关
     */
    public static class Profile {
        /**
         * 查询用户作品概况
         * @param userId           用户id
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.UserInfoBody>>
         * @since 2022/10/14 17:51
         */
        public static PixivRequest<PixivResponse<ProfileRealBody>> all(long userId,
                                                                       @NotNull SimpleParam param,
                                                                       @NotNull PixivCookieToken pixivCookieToken,
                                                                       @NotNull OkHttpClient client) {
            return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/profile/all", userId)
                    , client);
        }

        /**
         * 查询用户的绘画信息
         * @param userId           用户id
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileRealBody>>
         * @since 2022/10/15 11:18
         */
        public static PixivRequest<PixivResponse<ProfileIllustsBody>> illusts(long userId,
                                                                              @NotNull ProfileIllustsParam param,
                                                                              @NotNull PixivCookieToken pixivCookieToken,
                                                                              @NotNull OkHttpClient client) {
            return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/profile/illusts", userId)
                    , client);
        }

        /**
         * 查询用户的小说
         * @param userId           用户id
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.user.ProfileIllustsBody>>
         * @since 2022/10/17 11:10
         */
        public static PixivRequest<PixivResponse<ProfileNovelsBody>> novels(long userId,
                                                                            @NotNull ProfileNovelsParam param,
                                                                            @NotNull PixivCookieToken pixivCookieToken,
                                                                            @NotNull OkHttpClient client) {
            return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/user/%d/profile/novels", userId), client);
        }

        /**
         * 查询用户发出的约稿
         * @param userId           用户id
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
         * @since 2022/10/15 14:01
         */
        public static PixivRequest<PixivResponse<CommissionRequestSentBody>> commissionRequestSent(long userId,
                                                                                                   @NotNull SimpleParam param,
                                                                                                   @NotNull PixivCookieToken pixivCookieToken,
                                                                                                   @NotNull OkHttpClient client) {
            return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue), Pixiv.DOMAIN + "/ajax/commission/page/users/%d/request/sent", userId), client);
        }


        /**
         * 查询用户的小说中使用的标签
         * @param userId           用户id
         * @param param            参数
         * @param pixivCookieToken cooke和token
         * @param client           客户端
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserCommissionBody>>
         * @since 2022/10/15 14:01
         */
        public static PixivRequest<PixivResponse<List<UserTag>>> novelTags(long userId,
                                                                           @NotNull SimpleParam param,
                                                                           @NotNull PixivCookieToken pixivCookieToken,
                                                                           @NotNull OkHttpClient client) {
            return new PixivRequest<>(createHttpUrl(param, Collections.singleton(SerializerFeature.WriteMapNullValue)
                    , Pixiv.DOMAIN + "/ajax/user/%d/novels/tags", userId), client);
        }
    }


    /**
     * 用户的收藏相关
     */
    public static class Bookmark {
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
         * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.CommonBookmarkTagsBody>>
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


}
