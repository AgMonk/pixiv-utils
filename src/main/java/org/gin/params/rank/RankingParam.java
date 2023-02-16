package org.gin.params.rank;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.gin.emuns.PixivRankingMode;
import org.gin.emuns.RankingContent;

/**
 * 排行参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/18 09:40
 */
@Getter
public class RankingParam {
    @JsonProperty("p")
    final int page;
    final PixivRankingMode mode;
    final String format = "json";
    final RankingContent content;
    /**
     * 日期，格式：yyyyMMdd
     */
    final String date;

    public RankingParam(int page, PixivRankingMode mode, RankingContent content, String date) {
        this.page = page;
        this.mode = mode;
        this.content = content;
        this.date = date;
    }
}
