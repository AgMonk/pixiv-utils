package org.gin.response.body.illustmanga;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;
import org.gin.response.fields.ArtworkInfo;
import org.gin.response.fields.RecommendDetail;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 09:58
 */
@Getter
@Setter
public class IllustRecommendInitRes extends PixivResponse<IllustRecommendInitRes.IllustRecommendInitBody> {

    @Getter
    @Setter
    public static class IllustRecommendInitBody {
        HashMap<Long, RecommendDetail> details;
        List<ArtworkInfo> illusts;
        List<Long> nextIds;
    }
}
