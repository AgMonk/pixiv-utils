package org.gin.params.novel;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索小说的参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 10:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovelSearchParam {
    /**
     * 发布时间（止），以东九区为准，格式：yyyy-MM-dd
     */
    String ecd;
    /**
     * 是否以系列分组显示
     */
    Integer gs = 0;
    /**
     * 语言
     */
    String lang = "zh";
    /**
     * 模式 可选值：`all`、`safe`、`r18`
     */
    String mode = "all";
    /**
     * 排序模式: 可选值：`date_d`从新到旧(默认),`date`从旧到新
     */
    String order = "date_d";
    /**
     * 页码
     */
    @JSONField(name = "p")
    int page = 1;
    /**
     * 发布时间（起），以东九区为准，格式：yyyy-MM-dd
     */
    String scd;
    /**
     * 字数范围（最高），官方提供的字数范围选项为：`0-4999`,`5000-19999`,`20000-79999`,`80000-`，指定文字数为会员功能
     */
    Long tgt;
    /**
     * 字数范围（最低）
     */
    Long tlt;

    @JSONField(name = "work_lang")
    String workLang = "zh-cn";

    public NovelSearchParam(int page) {
        this.page = page;
    }
}
