package org.gin.params.user;

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
public class ProfileIllustsParam {
    /**
     * 是否为第一页。1 = 是 ，0 = 否。返回结果疑似没有区别
     */
    @JSONField(name = "is_first_page")
    final int isFirstPage = 1;
    /**
     * 作品类型，可选值：`illust`(插画)、`manga`(漫画)、`illustManga`(二者混合)。疑似不敏感，但是不能省略
     */
    @JSONField(name = "work_category")
    final String workCategory = "illustManga";
    /**
     * 需要查询的作品pid
     */
    @JSONField(name = "ids[]")
    List<Long> ids;
    /**
     * 语言
     */
    String lang = "zh";

    public ProfileIllustsParam(List<Long> ids) {
        this.ids = ids;
    }

    public ProfileIllustsParam(Long... ids) {
        this.ids = Arrays.asList(ids);
    }
}
