package org.gin.pixiv.response.body.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bx002
 */
@Getter
@Setter
public class LikeBody {
    /**
     * 操作之前是否已经喜欢该作品
     */
    @JsonProperty("is_liked")
    Boolean isLiked;
}