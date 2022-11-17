package org.gin.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 17:25
 */
@Getter
@Setter
@RequiredArgsConstructor
public class JsonUtils {

    public static void printJson(Object o) {
        System.out.println(JSONObject.toJSONString(o, SerializerFeature.PrettyFormat));
    }
}   
