package org.gin.api.groups;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.emuns.PixivRankingMode;
import org.gin.emuns.RankingContent;
import org.gin.exception.PixivRequestException;
import org.gin.params.rank.RankingParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.RankingResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

/**
 * 排行榜API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 16:29
 */
@Getter
@Setter
@RequiredArgsConstructor
public class RankingApi {
    private final PixivApi api;

    /**
     * 查询绘画排行榜
     * @param param 参数
     * @return org.gin.request.PixivRequest<org.gin.response.RankingResponse>
     * @since 2022/11/17 16:30
     */
    public PixivRequest<RankingResponse> illust(@NotNull RankingParam param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/ranking.php")
                .setParams(param)
                .build();
        return new PixivRequest<>(url, api.getClient(), responseBody -> {
            String string = responseBody.string();
            string = string.replace("\"illust_series\":false", "\"illust_series\":{}");
            return JSONObject.parseObject(string, RankingResponse.class);
        });

    }

    public void zTest() throws PixivRequestException, IOException {
        final RankingResponse res = illust(new RankingParam(1, PixivRankingMode.daily, RankingContent.illust, null)).sync();
        final List<RankingResponse.RankingData> contents = res.getContents();
        final RankingResponse.RankingData data = contents.get(0);
        System.out.println("data.getTitle() = " + data.getTitle());
    }

}   
