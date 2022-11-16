package org.gin.response.body.illustmanga;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;
import org.gin.response.fields.ArtworkInfo;
import org.gin.response.fields.Popular;
import org.gin.response.fields.TagTranslation;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:39
 **/
@Getter
@Setter
public class IllustMangaSearchRes extends PixivResponse<IllustMangaSearchRes.IllustMangaSearchBody> {

    @Getter
    @Setter
    public static class IllustMangaSearchBody {
        IllustManga illustManga;
        Popular popular;
        List<String> relatedTags;
        HashMap<String, TagTranslation> tagTranslation;


        @Getter
        @Setter
        public static class IllustManga {
            Integer total;
            List<ArtworkInfo> data;
        }
    }
}
