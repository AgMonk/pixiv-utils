package org.gin.api.groups;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.params.follow.FollowAddParam;
import org.gin.params.follow.FollowDelParam;
import org.gin.params.user.UserRecommendParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.body.user.UserInfoRes;
import org.gin.response.body.user.UserRecommendRes;
import org.jetbrains.annotations.NotNull;

import static org.gin.request.PixivRequestBody.createFormBody;

/**
 * 用户API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 17:22
 */
@RequiredArgsConstructor
public class UserApi {
    private final PixivApi api;

    /**
     * 关注用户
     * @param param 参数
     * @return org.gin.request.PixivRequest<java.lang.String>
     * @since 2022/11/16 17:28
     */
    public PixivRequest<String> follow(@NonNull FollowAddParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/bookmark_add.php")
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, createFormBody(param), api.getClient(), api.getCookieToken());
    }

    /**
     * 推荐用户
     * @param userId 用户id
     * @param param  参数
     * @return org.gin.request.PixivRequest<UserRecommendRes>
     * @since 2022/11/16 17:27
     */
    public PixivRequest<UserRecommendRes> recommend(long userId, @NotNull UserRecommendParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/%d/recommends", userId)
                .setParams(param)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

    /**
     * 取消关注
     * @param userId 用户id
     * @return org.gin.request.PixivRequest<java.lang.String>
     * @since 2022/10/17 15:49
     */
    public PixivRequest<String> unfollow(long userId) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/rpc_group_setting.php")
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, createFormBody(new FollowDelParam(userId)), api.getClient(), api.getCookieToken());
    }

    /**
     * 查询用户信息
     * @param userId   用户id
     * @param fullData 是否查询完整信息
     * @return org.gin.request.PixivRequest<org.gin.response.body.user.UserInfoRes>
     * @since 2022/11/16 17:25
     */
    public PixivRequest<UserInfoRes> userInfo(long userId, boolean fullData) {

        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ajax/user/" + userId)
                .addParam("full", fullData ? 1 : 0)
                .setLang(api.getLang())
                .build();
        return new PixivRequest<>(url, api.getClient(), api.getCookieToken());
    }

}
