package org.gin.response.body.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/1 10:16
 */
@Data
public class SuggestByWordBody {
    List<Candidate> candidates;

    @Data
    public static class Candidate {
        @JsonProperty("illust_count")
        Long illustCount;
        @JsonProperty("tag_name")
        String tagName;
        @JsonProperty("total_count")
        Long totalCount;
    }
}
