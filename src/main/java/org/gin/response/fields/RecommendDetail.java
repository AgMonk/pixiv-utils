package org.gin.response.fields;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 09:59
 */
@Getter
@Setter
public class RecommendDetail {
    String banditInfo;
    List<String> methods;
    String recommendListId;
    Double score;
    List<Long> seedIllustIds;
}
