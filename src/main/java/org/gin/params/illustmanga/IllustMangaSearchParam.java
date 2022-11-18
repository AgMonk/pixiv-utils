package org.gin.params.illustmanga;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gin.emuns.PixivIllustSearchMode;
import org.gin.emuns.PixivIllustSearchModeSerializer;
import org.gin.params.BaseSearchParam;

/**
 * 插画/漫画搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:17
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IllustMangaSearchParam extends BaseSearchParam {

    @JSONField(name = "s_mode", serializeUsing = PixivIllustSearchModeSerializer.class)
    PixivIllustSearchMode searchMode = PixivIllustSearchMode.DEFAULT;

    public IllustMangaSearchParam(int page) {
        super(page);
    }
}
