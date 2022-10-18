package org.gin.api;

import okhttp3.OkHttpClient;
import org.gin.params.rank.RankParam;
import org.gin.request.Pixiv;
import org.gin.request.PixivCookieToken;
import org.gin.request.PixivRequest;
import org.gin.response.RankingResponse;
import org.jetbrains.annotations.NotNull;

import static org.gin.request.PixivUrl.createHttpUrl;

/**
 * 排行榜Api
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/18 09:33
 */
public class ApiRanking {
    /**
     * 查询图片排行榜数据
     * @param page             页码
     * @param mode             模式
     * @param content          正文类型
     * @param date             日期 yyyyMMdd格式
     * @param pixivCookieToken cooke和token
     * @param client           客户端
     * @return org.gin.request.PixivRequest<org.gin.response.RankingResponse>
     * @since 2022/10/18 9:42
     */
    public static PixivRequest<RankingResponse> illust(@NotNull RankParam param,
                                                       @NotNull PixivCookieToken pixivCookieToken,
                                                       @NotNull OkHttpClient client) {
        return new PixivRequest<>(createHttpUrl(param, null,
                Pixiv.DOMAIN + "/ranking.php"), client, pixivCookieToken);
    }


}
