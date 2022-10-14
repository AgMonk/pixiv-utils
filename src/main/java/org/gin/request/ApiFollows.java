package org.gin.request;

import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.exception.PixivUrlCreateException;
import org.gin.params.FollowLatestParam;
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
    * @param pixivCookieToken cooke和token
    * @param client 客户端
    * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.FollowLatestBody>>
    * @author bx002
    * @since 2022/10/14 17:05
    */
    public static PixivRequest<PixivResponse<FollowLatestBody>> followLatest(@NonNull FollowLatestParam followLatestParam,
                                                                             @NonNull PixivCookieToken pixivCookieToken,
                                                                             @NonNull OkHttpClient client
    ) throws PixivUrlCreateException {
        return new PixivRequest<>(PixivCommon.createHttpUrl(followLatestParam, PixivUrl.DOMAIN + "/ajax/follow_latest/illust")
                , client, pixivCookieToken);
    }


    //todo 关注用户
    //todo 取消关注
}
