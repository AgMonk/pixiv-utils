package org.gin.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

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

    /**
     * 把对象转换为HashMap
     * @param obj 对象 推荐使用HashMap ，传入null的字段会传递空串
     * @return HashMap
     */
    public static HashMap<String, Object> jsonToMap(Object obj) {
        return JSONObject.parseObject(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue), new TypeReference<HashMap<String, Object>>() {
        });
    }
}
