package org.gin.response.body.illustmanga;

import lombok.Data;
import org.gin.response.fields.TagTranslation;
import org.gin.response.fields.Thumbnails;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/21 09:09
 */
@Data
public class DiscoveryBody {
    List<RecommendedIllust> recommendedIllusts;
    HashMap<String, TagTranslation> tagTranslation;
    Thumbnails thumbnails;

    @Data
    static class RecommendedIllust {
        Long illustId;
        List<String> recommendMethods;
        Double recommendScore;
        List<Long> recommendSeedIllustIds;
    }
}
