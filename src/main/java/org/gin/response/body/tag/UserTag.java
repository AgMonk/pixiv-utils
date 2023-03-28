package org.gin.response.body.tag;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户在作品中使用的标签
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/15 17:53
 */
@Getter
@Setter
public class UserTag {
    @JsonProperty("cnt")
    long count;
    String tag;
    @JsonProperty("tag_translation")
    String translation;
    @JsonProperty("tag_yomigana")
    String yomigana;

}
