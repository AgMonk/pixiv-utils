package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.gin.utils.JsonUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * 请求Body工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 15:23
 */
public class PixivRequestBody {
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 创建 RequestBody
     * @param key   key
     * @param value value
     * @return okhttp3.RequestBody
     * @since 2022/10/15 14:24
     */
    public static RequestBody createJsonBody(String key, Serializable value) {
        final HashMap<String, Object> map = new HashMap<>(1);
        map.put(key, value);
        return createJsonBody(map);
    }

    /**
     * 创建 RequestBody
     * @param key   key
     * @param value value
     * @return okhttp3.RequestBody
     * @since 2022/10/15 14:24
     */
    public static RequestBody createJsonBody(String key, Collection<?> value) {
        final HashMap<String, Object> map = new HashMap<>(1);
        map.put(key, value);
        return createJsonBody(map);
    }

    /**
     * 创建 RequestBody
     * @param obj 对象 推荐使用HashMap ，传入null的字段会传递空串
     * @return RequestBody
     */
    public static RequestBody createJsonBody(Object obj) {
        return RequestBody.create(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue), MEDIA_TYPE_JSON);
    }

    /**
     * 创建 FormBody
     * @param key   key
     * @param value value
     * @return FormBody
     */
    public static FormBody createFormBody(String key, Serializable value) {
        final HashMap<String, String> map = new HashMap<>(1);
        map.put(key, String.valueOf(value));
        return createFormBody(map);
    }

    /**
     * 创建 FormBody
     * @param obj 对象 推荐使用HashMap ，传入null的字段会传递空串
     * @return FormBody
     */
    public static FormBody createFormBody(Object obj) {
        final HashMap<String, Object> map = JsonUtils.jsonToMap(obj);
        final FormBody.Builder builder = new FormBody.Builder();
        map.forEach((k, v) -> {
            if (v != null) {
                builder.add(k, String.valueOf(v));
            }
        });
        return builder.build();
    }
}
