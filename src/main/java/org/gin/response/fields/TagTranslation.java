package org.gin.response.fields;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:45
 **/
@Getter
@Setter
public class TagTranslation {
    String en;
    String ko;
    String zh;
    String romaji;
    @JsonProperty("zh_tw")
    String zhTw;
}
