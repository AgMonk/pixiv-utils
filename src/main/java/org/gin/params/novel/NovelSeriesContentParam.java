package org.gin.params.novel;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.gin.emuns.PixivOrderBy;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 16:25
 */
@Getter
@Setter
@AllArgsConstructor
public class NovelSeriesContentParam {
    @JSONField(name = "limit")
    int size;
    /**
     * 排序
     */
    @JSONField(name = "order_by")
    PixivOrderBy orderBy = PixivOrderBy.asc;
    @JSONField(name = "last_order")
    int offset;

    public NovelSeriesContentParam(int page, int size) {
        this.size = size;
        this.offset = Math.max(0, page - 1) * size;
    }
}
