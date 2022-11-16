package org.gin.response.body.illustmanga;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;
import org.gin.response.fields.TagTranslation;
import org.gin.response.fields.Thumbnails;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 17:01
 **/
@Getter
@Setter
public class IllustMangaFollowLatestRes extends PixivResponse<IllustMangaFollowLatestRes.IllustMangaFollowLatestBody> {

    @Getter
    @Setter
    public static class IllustMangaFollowLatestBody {
        Page page;
        Thumbnails thumbnails;
        HashMap<String, TagTranslation> tagTranslation;

        @Getter
        @Setter
        static class Page {
            List<Long> ids;
            Boolean isLastPage;
        }
    }
}
