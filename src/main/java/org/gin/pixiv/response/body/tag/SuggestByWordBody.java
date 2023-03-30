package org.gin.pixiv.response.body.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 10:16
 */
@Getter
@Setter
public class SuggestByWordBody {
    List<Candidate> candidates;

    @Getter
    @Setter
    public static class Candidate {
        @JsonProperty("illust_count")
        Long illustCount;
        @JsonProperty("tag_name")
        String tagName;
        @JsonProperty("total_count")
        Long totalCount;
    }
}
