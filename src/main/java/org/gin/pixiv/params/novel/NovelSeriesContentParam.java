package org.gin.pixiv.params.novel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.enums.PixivOrderBy;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 16:25
 */
@Getter
@Setter
@AllArgsConstructor
public class NovelSeriesContentParam {
    @JsonProperty("limit")
    int size;
    /**
     * 排序
     */
    @JsonProperty("order_by")
    PixivOrderBy orderBy = PixivOrderBy.asc;
    @JsonProperty("last_order")
    int offset;

    public NovelSeriesContentParam(int page, int size) {
        this.size = size;
        this.offset = Math.max(0, page - 1) * size;
    }
}
