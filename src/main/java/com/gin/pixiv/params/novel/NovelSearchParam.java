package com.gin.pixiv.params.novel;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.pixiv.enums.PixivNovelSearchMode;
import com.gin.pixiv.enums.PixivWorkLang;
import com.gin.pixiv.params.BaseSearchParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonProperty("gs")
    Integer groupBySeries = 0;
    /**
     * 字数范围（最高），官方提供的字数范围选项为：`0-4999`,`5000-19999`,`20000-79999`,`80000-`，指定文字数为会员功能
     */
    @JsonProperty("tgt")
    Integer wordCountMax;
    /**
     * 字数范围（最低）
     */
    @JsonProperty("tlt")
    Integer wordCountMin;

    @JsonProperty("work_lang")
    PixivWorkLang workLang = PixivWorkLang.ZH_CN;

    @JsonProperty("s_mode")
    PixivNovelSearchMode searchMode = PixivNovelSearchMode.DEFAULT;

    public NovelSearchParam(int page) {
        super(page);
    }
}
