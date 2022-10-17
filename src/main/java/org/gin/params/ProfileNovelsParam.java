package org.gin.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 11:06
 **/
@Data
public class ProfileNovelsParam {
    /**
     * 需要查询的作品pid
     */
    @JSONField(name = "ids[]")
    List<Long> ids;
    /**
     * 语言
     */
    String lang = "zh";

    public ProfileNovelsParam(List<Long> ids) {
        this.ids = ids;
    }

    public ProfileNovelsParam(Long... ids) {
        this.ids = Arrays.asList(ids);
    }
}
