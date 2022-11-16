package org.gin.response.body.illustmanga;

import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;
import org.gin.response.fields.ArtworkInfo;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 09:58
 */
@Getter
@Setter
public class IllustRecommendRes extends PixivResponse<IllustRecommendRes.IllustRecommendBody> {

    @Getter
    @Setter
    public static class IllustRecommendBody {
        List<ArtworkInfo> illusts;
    }
}
