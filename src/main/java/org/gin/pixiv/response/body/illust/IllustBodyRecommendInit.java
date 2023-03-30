package org.gin.pixiv.response.body.illust;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

/**
 * 推荐作品body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 17:15
 */
@Getter
@Setter
public class IllustBodyRecommendInit extends IllustBodyRecommend {
    HashMap<Long, RecommendDetail> details;
    List<IllustBodySimple> illusts;
    List<Long> nextIds;


    @Getter
    @Setter
    public static class RecommendDetail {
        String banditInfo;
        List<String> methods;
        String recommendListId;
        Double score;
        List<Long> seedIllustIds;
    }
}   
