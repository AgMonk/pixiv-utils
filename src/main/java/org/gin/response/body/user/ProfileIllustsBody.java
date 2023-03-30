package org.gin.response.body.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.response.body.illust.IllustBodySimple;

import java.util.LinkedHashMap;

/**
 * 用户作品Body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 14:01
 **/
@Getter
@Setter
public class ProfileIllustsBody {
    @JsonProperty("works")
    LinkedHashMap<Long, IllustBodySimple> illusts;
}
