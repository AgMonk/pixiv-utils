package org.gin.params.illustmanga;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 11:06
 **/
@Data
public class RecommendIllustsParam {
    /**
     * 基准作品pid
     */
    @JSONField(name = "illust_ids[]")
    List<Long> ids;
    /**
     * 语言
     */
    String lang = "zh";

    public RecommendIllustsParam(List<Long> ids) {
        this.ids = ids;
    }

    public RecommendIllustsParam(List<Long> ids, String lang) {
        this.ids = ids;
        this.lang = lang;
    }
}
