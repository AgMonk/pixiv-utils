package org.gin.pixiv.response.body.illust;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.response.body.search.BaseSearchBody;

import java.util.List;

/**
 * 绘画作品的搜索结果body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 13:05
 */
@Getter
@Setter
public class IllustBodySearch extends BaseSearchBody {
    @JsonAlias({"illust", "illustManga"})
    IllustManga illustManga;
    Popular popular;

    @Getter
    @Setter
    public static class IllustManga {
        Integer total;
        List<IllustBodySimple> data;
    }

    @Getter
    @Setter
    public static class Popular {
        /**
         * 长期的
         */
        List<IllustBodySimple> permanent;
        /**
         * 最近的
         */
        List<IllustBodySimple> recent;
    }

}
