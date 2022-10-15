package org.gin.request;

import okhttp3.OkHttpClient;
import org.gin.exception.PixivUrlCreateException;
import org.gin.params.ProfileIllustsParam;
import org.gin.params.SimpleParam;
import org.gin.params.UserInfoParam;
import org.gin.response.PixivResponse;
import org.gin.response.body.ProfileIllustsBody;
import org.gin.response.body.ProfileRealBody;
import org.gin.response.body.UserInfoBody;
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
 * @param userId 用户id
 * @param param 参数
 * @param pixivCookieToken cooke和token
 * @param client 客户端
 * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.UserInfoBody>>
 * @author bx002
 * @since 2022/10/14 17:47
 */
public static PixivRequest<PixivResponse<UserInfoBody>> userInfo(int userId,
                                                                 @NotNull UserInfoParam param,
                                                                 @NotNull PixivCookieToken pixivCookieToken,
                                                                 @NotNull OkHttpClient client) throws PixivUrlCreateException {
    return new PixivRequest<>(PixivCommon.createHttpUrl(param, PixivUrl.DOMAIN + "/ajax/user/%d", userId)
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
    public static PixivRequest<PixivResponse<ProfileRealBody>> profileAll(int userId,
                                                                          @NotNull SimpleParam param,
                                                                          @NotNull PixivCookieToken pixivCookieToken,
                                                                          @NotNull OkHttpClient client) throws PixivUrlCreateException {
        return new PixivRequest<>(PixivCommon.createHttpUrl(param, PixivUrl.DOMAIN + "/ajax/user/%d/profile/all", userId)
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
    public static PixivRequest<PixivResponse<ProfileIllustsBody>> profileIllusts(int userId,
                                                                                 @NotNull ProfileIllustsParam param,
                                                                                 @NotNull PixivCookieToken pixivCookieToken,
                                                                                 @NotNull OkHttpClient client) throws PixivUrlCreateException {
        return new PixivRequest<>(PixivCommon.createHttpUrl(param, PixivUrl.DOMAIN + "/ajax/user/%d/profile/illusts", userId)
                , client, pixivCookieToken);
    }


    //todo 查询用户的作品信息
    //todo 查询用户的收藏
    //todo 查询用户收藏中使用的标签
    //todo 查询用户发出的约稿
}
