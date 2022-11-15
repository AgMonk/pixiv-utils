package org.gin.response.body.tag;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 用户在作品中使用的标签
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/15 17:53
 */
@Data
public class UserTag {
    @JSONField(alternateNames = "cnt")
    long count;
    String tag;
    @JSONField(alternateNames = "tag_translation")
    String translation;
    @JSONField(alternateNames = "tag_yomigana")
    String yomigana;

}
