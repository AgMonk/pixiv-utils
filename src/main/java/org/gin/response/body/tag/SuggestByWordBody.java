package org.gin.response.body.tag;

import com.alibaba.fastjson.annotation.JSONField;
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
        @JSONField(alternateNames = "illust_count")
        Long illustCount;
        @JSONField(alternateNames = "tag_name")
        String tagName;
        @JSONField(alternateNames = "total_count")
        Long totalCount;
    }
}
