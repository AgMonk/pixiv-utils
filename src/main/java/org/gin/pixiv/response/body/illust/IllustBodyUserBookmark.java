package org.gin.pixiv.response.body.illust;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户收藏的绘画
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/30 10:42
 */
@Getter
@Setter
public class IllustBodyUserBookmark {
    Long total;
    @JsonProperty("works")
    List<IllustBodySimple> data;
}
