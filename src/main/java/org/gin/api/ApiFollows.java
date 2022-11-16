package org.gin.api;

import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.params.follow.FollowAddParam;
import org.gin.params.follow.FollowDelParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;

import static org.gin.request.PixivRequestBody.createFormBody;
import static org.gin.request.PixivUrl.createHttpUrl;

/**
 * 关注相关接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 16:59
 **/
public class ApiFollows {

    /**
     * 关注用户
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.illustmanga.FollowLatestBody>>
     * @since 2022/10/15 15:05
     */
    public static PixivRequest<String> add(@NonNull FollowAddParam param,
                                           @NonNull PixivCookieToken pixivCookieToken,
                                           @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(createHttpUrl(Pixiv.DOMAIN + "/bookmark_add.php")
                , createFormBody(param)
                , client, pixivCookieToken);
    }

    /**
     * 取消关注
     * @param userId           用户id
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<java.lang.String>
     * @since 2022/10/17 15:49
     */
    public static PixivRequest<String> del(long userId,
                                           @NonNull PixivCookieToken pixivCookieToken,
                                           @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(createHttpUrl(Pixiv.DOMAIN + "/rpc_group_setting.php")
                , createFormBody(new FollowDelParam(userId))
                , client, pixivCookieToken);
    }


}
