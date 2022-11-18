package org.gin.params.novel;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gin.emuns.*;

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
    @JSONField(name = "ecd")
    String dateEnd;
    /**
     * 是否以系列分组显示
     */
    @JSONField(name = "gs")
    Integer groupBySeries = 0;
    /**
     * 模式
     */
    PixivMode mode = PixivMode.all;
    /**
     * 排序模式
     */
    @JSONField(serializeUsing = PixivOrderSerializer.class)
    PixivOrder order = PixivOrder.DSC;
    /**
     * 页码
     */
    @JSONField(name = "p")
    int page = 1;
    /**
     * 发布时间（起），以东九区为准，格式：yyyy-MM-dd
     */
    @JSONField(name = "scd")
    String dateStart;
    /**
     * 字数范围（最高），官方提供的字数范围选项为：`0-4999`,`5000-19999`,`20000-79999`,`80000-`，指定文字数为会员功能
     */
    @JSONField(name = "tgt")
    Integer wordCountMax;
    /**
     * 字数范围（最低）
     */
    @JSONField(name = "tlt")
    Integer wordCountMin;

    @JSONField(name = "work_lang", serializeUsing = PixivWorkLangSerializer.class)
    PixivWorkLang workLang = PixivWorkLang.ZH_CN;

    public NovelSearchParam(int page) {
        this.page = page;
    }
}
