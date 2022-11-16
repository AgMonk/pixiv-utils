package org.gin.params.novel;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 16:25
 */
@Getter
@Setter
@AllArgsConstructor
public class NovelSeriesContentParam {
    int limit = 10;
    /**
     * 排序，可选值：asc dsc
     */
    @JSONField(name = "order_by")
    String orderBy = "asc";
    @JSONField(name = "last_order")
    int lastOrder;

    public NovelSeriesContentParam(int page, int size) {
        this.limit = size;
        this.lastOrder = Math.max(0, page - 1) * size;
    }
}
