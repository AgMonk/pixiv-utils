package org.gin.api;

import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.gin.params.NovelSearchParam;
import org.gin.params.SimpleParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.PixivResponse;
import org.gin.response.body.NovelBody;
import org.gin.response.body.NovelSearchBody;
import org.jetbrains.annotations.NotNull;

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

    /**
     * 搜索
     * @param keywords         关键字
     * @param param            参数
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.PixivResponse < ?>>
     * @since 2022/10/17 10:30
     */
    public static PixivRequest<PixivResponse<NovelSearchBody>> search(@NotNull String keywords,
                                                                      @NotNull NovelSearchParam param,
                                                                      @NotNull PixivCookieToken pixivCookieToken,
                                                                      @NotNull OkHttpClient client) {
        return new PixivRequest<>(
                PixivUrl.createHttpUrl(param, Pixiv.DOMAIN + "/ajax/search/novels/%s", keywords),
                client, pixivCookieToken
        );
    }

    //todo 查询小说系列

}
