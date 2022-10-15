package org.gin.request;

import okhttp3.OkHttpClient;
import org.gin.params.IllustsBookmarksParam;
import org.gin.params.ProfileIllustsParam;
import org.gin.params.SimpleParam;
import org.gin.params.UserInfoParam;
import org.gin.response.PixivResponse;
import org.gin.response.body.*;
import org.jetbrains.annotations.NotNull;

/**
 * 用户相关接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 17:39
 **/
public class ApiUser {
    /**
     * 查询用户信息
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserInfoBody>>
     * @since 2022/10/14 17:47
     */
    public static PixivRequest<PixivResponse<UserInfoBody>> userInfo(long userId,
                                                                     @NotNull UserInfoParam param,
                                                                     @NotNull PixivCookieToken pixivCookieToken,
                                                                     @NotNull OkHttpClient client) {
        return new PixivRequest<>(PixivCommon.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/user/%d", userId)
                , client, pixivCookieToken);
    }

    /**
     * 查询用户作品概况
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserInfoBody>>
     * @since 2022/10/14 17:51
     */
    public static PixivRequest<PixivResponse<ProfileRealBody>> profileAll(long userId,
                                                                          @NotNull SimpleParam param,
                                                                          @NotNull PixivCookieToken pixivCookieToken,
                                                                          @NotNull OkHttpClient client) {
        return new PixivRequest<>(PixivCommon.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/user/%d/profile/all", userId)
                , client, pixivCookieToken);
    }

    /**
     * 查询用户的作品信息
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.ProfileRealBody>>
     * @since 2022/10/15 11:18
     */
    public static PixivRequest<PixivResponse<ProfileIllustsBody>> profileIllusts(long userId,
                                                                                 @NotNull ProfileIllustsParam param,
                                                                                 @NotNull PixivCookieToken pixivCookieToken,
                                                                                 @NotNull OkHttpClient client) {
        return new PixivRequest<>(PixivCommon.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/user/%d/profile/illusts", userId)
                , client, pixivCookieToken);
    }

    /**
     * 查询用户的收藏
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.IllustsBookmarksBody>>
     * @since 2022/10/15 12:19
     */
    public static PixivRequest<PixivResponse<IllustsBookmarksBody>> illustsBookmarks(long userId,
                                                                                     @NotNull IllustsBookmarksParam param,
                                                                                     @NotNull PixivCookieToken pixivCookieToken,
                                                                                     @NotNull OkHttpClient client) {
        return new PixivRequest<>(PixivCommon.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/user/%d/illusts/bookmarks", userId)
                , client, pixivCookieToken);
    }

    /**
     * 查询用户收藏中使用的标签
     * @param userId           用户id
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.IllustsBookmarksBody>>
     * @since 2022/10/15 12:29
     */
    public static PixivRequest<PixivResponse<IllustBookmarkTagsBody>> illustsBookmarkTags(long userId,
                                                                                          @NotNull SimpleParam param,
                                                                                          @NotNull PixivCookieToken pixivCookieToken,
                                                                                          @NotNull OkHttpClient client) {
        return new PixivRequest<>(PixivCommon.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/user/%d/illusts/bookmark/tags", userId)
                , client, pixivCookieToken);
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
        return new PixivRequest<>(PixivCommon.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/commission/page/users/%d/request/sent", userId)
                , client, pixivCookieToken);
    }
}
