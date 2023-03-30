package com.gin.pixiv.response.body.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.pixiv.response.body.illust.IllustBodySimple;
import lombok.Getter;
import lombok.Setter;

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
