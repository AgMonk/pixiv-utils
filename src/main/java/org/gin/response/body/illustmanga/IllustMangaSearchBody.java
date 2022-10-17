package org.gin.response.body.illustmanga;

import lombok.Data;
import org.gin.response.fields.ArtworkInfo;
import org.gin.response.fields.TagTranslation;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:39
 **/
@Data
public class IllustMangaSearchBody {
    IllustManga illustManga;
    Popular popular;
    List<String> relatedTags;
    HashMap<String, TagTranslation> tagTranslation;


    @Data
    static class IllustManga {
        Integer total;
        List<ArtworkInfo> data;
    }

    @Data
    static class Popular{
        /**
         * 长期的
         */
        List<ArtworkInfo> permanent;
        /**
         * 最近的
         */
        List<ArtworkInfo> recent;
    }
}
