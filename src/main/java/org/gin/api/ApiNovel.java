package org.gin.api;

import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.params.SimpleParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.NovelBody;

/**
 * 小说接口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 09:19
 */
public class ApiNovel {
    /**
     * 查询小说详情
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < org.gin.response.body.FollowLatestBody>>
     * @since 2022/10/17 9:23
     */
    public static PixivRequest<PixivResponse<NovelBody>> detail(long nid,
                                                                @NonNull SimpleParam param,
                                                                @NonNull PixivCookieToken pixivCookieToken,
                                                                @NonNull OkHttpClient client
    ) {
        return new PixivRequest<>(PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/novel/" + nid)
                , client, pixivCookieToken);
    }
    //todo 搜索
    //todo 查询小说系列

}
