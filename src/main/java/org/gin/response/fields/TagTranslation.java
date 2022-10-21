package org.gin.response.fields;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:45
 **/
@Data
public class TagTranslation {
    String en;
    String ko;
    String zh;
    String romaji;
    @JSONField(alternateNames = "zh_tw")
    String zhTw;
}
