package org.gin.params.novel;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gin.emuns.PixivNovelSearchMode;
import org.gin.emuns.PixivNovelSearchModeSerializer;
import org.gin.emuns.PixivWorkLang;
import org.gin.emuns.PixivWorkLangSerializer;
import org.gin.params.BaseSearchParam;

/**
 * 搜索小说的参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/17 10:20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NovelSearchParam extends BaseSearchParam {
    /**
     * 是否以系列分组显示
     */
    @JSONField(name = "gs")
    Integer groupBySeries = 0;
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

    @JSONField(name = "s_mode", serializeUsing = PixivNovelSearchModeSerializer.class)
    PixivNovelSearchMode searchMode = PixivNovelSearchMode.DEFAULT;

    public NovelSearchParam(int page) {
        super(page);
    }
}
