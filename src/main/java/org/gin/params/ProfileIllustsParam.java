package org.gin.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 11:06
 **/
@Data
public class ProfileIllustsParam {
    /**
     * 语言
     */
    String lang = "zh";
    /**
     * 是否为第一页。1 = 是 ，0 = 否。返回结果疑似没有区别
     */
    @JSONField(name = "is_first_page")
    int isFirstPage = 1;
    /**
     * 作品类型，可选值：`illust`(插画)、`manga`(漫画)、`illustManga`(二者混合)。疑似不敏感，但是不能省略
     */
    @JSONField(name = "work_category")
    String workCategory = "illustManga";
    /**
     * 需要查询的作品pid
     */
    @JSONField(name = "ids[]")
    List<Integer> ids;

    public ProfileIllustsParam(List<Integer> ids) {
        this.ids = ids;
    }
}
