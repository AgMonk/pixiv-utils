package org.gin.response.body.illustmanga;

import lombok.Data;
import org.gin.response.fields.ArtworkInfo;
import org.gin.response.fields.RecommendDetail;

import java.util.HashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 09:58
 */
@Data
public class IllustRecommendInitBody {
    HashMap<Long, RecommendDetail> details;
    List<ArtworkInfo> illusts;
    List<Long> nextIds;
}
