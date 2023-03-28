package org.gin.response.body;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.gin.response.PixivResponse;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 16:07
 */
@Getter
@Setter
public class LikeRes extends PixivResponse<LikeRes.LikeBody> {

    @Getter
    @Setter
    public static class LikeBody {
        /**
         * 操作之前是否已经喜欢该作品
         */
        @JsonProperty("is_liked")
        Boolean isLiked;
    }
}
