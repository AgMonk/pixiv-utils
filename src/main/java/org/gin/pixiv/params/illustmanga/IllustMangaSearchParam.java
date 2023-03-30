package org.gin.pixiv.params.illustmanga;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gin.pixiv.enums.IllustSearchType;
import org.gin.pixiv.enums.PixivIllustSearchMode;
import org.gin.pixiv.params.BaseSearchParam;

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
    @JsonProperty("s_mode")
    PixivIllustSearchMode searchMode = PixivIllustSearchMode.DEFAULT;

    IllustSearchType type = IllustSearchType.all;

    public IllustMangaSearchParam(int page) {
        super(page);
    }
}
