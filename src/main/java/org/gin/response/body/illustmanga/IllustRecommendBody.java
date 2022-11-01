package org.gin.response.body.illustmanga;

import lombok.Data;
import org.gin.response.fields.ArtworkInfo;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 09:58
 */
@Data
public class IllustRecommendBody {
    List<ArtworkInfo> illusts;
}
