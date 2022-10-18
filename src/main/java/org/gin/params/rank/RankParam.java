package org.gin.params.rank;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;

/**
 * 排行参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/18 09:40
 */
@Getter
public class RankParam {
    @JSONField(name = "p")
    final int page;
    final String mode;
    final String format = "json";
    final String content;
    /**
     * 日期，格式：yyyyMMdd
     */
    final String date;
    String lang = "zh";

    public RankParam(int page, RankMode mode, RankContent content, String date) {
        this.page = page;
        this.mode = mode.toString();
        this.content = content.toString();
        this.date = date;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


}
