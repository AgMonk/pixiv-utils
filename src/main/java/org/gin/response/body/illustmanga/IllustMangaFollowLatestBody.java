package org.gin.response.body.illustmanga;

import lombok.Data;
import org.gin.response.fields.TagTranslation;
import org.gin.response.fields.Thumbnails;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 17:01
 **/
@Data
public class IllustMangaFollowLatestBody {
    Page page;
    Thumbnails thumbnails;
    HashMap<String, TagTranslation> tagTranslation;

    @Data
    static class Page {
        List<Long> ids;
        Boolean isLastPage;
    }


}
