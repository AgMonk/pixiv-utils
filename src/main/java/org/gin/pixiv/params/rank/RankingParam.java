package org.gin.pixiv.params.rank;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.gin.pixiv.enums.PixivRankingMode;
import org.gin.pixiv.enums.RankingContent;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

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
     * 指定日期排行，最多只能请求当前日期-2天的数据
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    final LocalDate date;

    public RankingParam(int page, @NotNull PixivRankingMode mode, @NotNull RankingContent content, LocalDate date) {
        this.page = page;
        this.mode = mode;
        this.content = content;
        this.date = date;
    }
}
