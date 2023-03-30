package com.gin.pixiv.api;

import com.gin.pixiv.call.PixivCallType;
import com.gin.pixiv.enums.PixivRankingMode;
import com.gin.pixiv.enums.RankingContent;
import com.gin.pixiv.main.PixivClient;
import com.gin.pixiv.params.rank.RankingParam;
import com.gin.pixiv.response.body.RankingResponse;
import com.gin.utils.JsonUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
     * @return {@link PixivCallType < RankingResponse >}
     * @since 2023/3/29 10:44
     */
    public PixivCallType<RankingResponse> getIllust(@NotNull RankingParam param) {
        return client.type(RANKING_PATH, RankingResponse.class, param);
    }

    public void zTest() throws IOException {
        final RankingResponse res = getIllust(new RankingParam(1, PixivRankingMode.daily, RankingContent.manga, LocalDate.now().minusDays(2))).sync();
        final List<RankingResponse.RankingData> contents = res.getContents();
        JsonUtils.printJson(contents);
    }
}   
