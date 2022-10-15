package org.gin.api;

import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.params.FollowAddParam;
import org.gin.params.FollowDelParam;
import org.gin.params.FollowLatestParam;
import org.gin.request.*;
import org.gin.response.PixivResponse;
import org.gin.response.body.FollowLatestBody;

/**
 * 关注相关接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 16:59
 **/
public class ApiFollows {
    /**
     * 查询关注作者的最新作品
     * @param followLatestParam 参数
     * @param pixivCookieToken  cooke和token
     * @param client            客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.FollowLatestBody>>
     * @since 2022/10/14 17:05
     */
    public static PixivRequest<PixivResponse<FollowLatestBody>> latest(@NonNull FollowLatestParam followLatestParam,
                                                                       @NonNull PixivCookieToken pixivCookieToken,
                                                                       @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(PixivUrl.createHttpUrl(followLatestParam, Pixiv.DOMAIN + "/ajax/follow_latest/illust")
                , client, pixivCookieToken);
    }

    /**
     * 关注用户
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.FollowLatestBody>>
     * @since 2022/10/15 15:05
     */
    public static PixivRequest<String> add(@NonNull FollowAddParam param,
                                           @NonNull PixivCookieToken pixivCookieToken,
                                           @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(PixivUrl.createHttpUrl(Pixiv.DOMAIN + "/bookmark_add.php")
                , PixivRequestBody.createFormBody(param)
                , client, pixivCookieToken);
    }

    /**
     * 取消关注
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<java.lang.String>
     * @since 2022/10/15 15:15
     */
    public static PixivRequest<String> del(@NonNull FollowDelParam param,
                                           @NonNull PixivCookieToken pixivCookieToken,
                                           @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(PixivUrl.createHttpUrl(Pixiv.DOMAIN + "/rpc_group_setting.php")
                , PixivRequestBody.createFormBody(param)
                , client, pixivCookieToken);
    }


}
