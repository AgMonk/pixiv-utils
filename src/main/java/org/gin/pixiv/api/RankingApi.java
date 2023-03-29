package org.gin.pixiv.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gin.emuns.PixivRankingMode;
import org.gin.emuns.RankingContent;
import org.gin.params.rank.RankingParam;
import org.gin.pixiv.call.PixivCallType;
import org.gin.pixiv.main.PixivClient;
import org.gin.response.RankingResponse;
import org.gin.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * 排行榜API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 09:52
 */
@Getter
@RequiredArgsConstructor
public class RankingApi {
    public static final String RANKING_PATH = "/ranking.php";
    private final PixivClient client;

    /**
     * 获取绘画作品排行
     * @param param 参数
     * @return {@link org.gin.pixiv.call.PixivCallType<org.gin.response.RankingResponse>}
     * @since 2023/3/29 10:44
     */
    public PixivCallType<RankingResponse> getIllust(@NotNull RankingParam param) {
        return client.type(RANKING_PATH, param, RankingResponse.class);
    }

    public void zTest() throws IOException {
        final RankingResponse res = getIllust(new RankingParam(1, PixivRankingMode.daily, RankingContent.manga, LocalDate.now().minusDays(2))).sync();
        final List<RankingResponse.RankingData> contents = res.getContents();
        JsonUtils.printJson(contents);
    }
}   
