package org.gin.pixiv.response.body.common;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.fields.TagTranslation;
import org.gin.response.fields.Thumbnails;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 15:50
 */
@Getter
@Setter
public class DiscoveryBody {
    List<RecommendedIllust> recommendedIllusts;
    //    Object tagTranslation;
    HashMap<String, TagTranslation> tagTranslation;

    Thumbnails thumbnails;
    List<Long> recommendedNovelIds;

    @Getter
    @Setter
    public static class RecommendedIllust {
        Long illustId;
        List<String> recommendMethods;
        Double recommendScore;
        List<Long> recommendSeedIllustIds;
    }
}
