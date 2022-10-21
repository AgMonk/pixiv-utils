package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.OkHttpClient;
import org.gin.interceptor.LoggingInterceptor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 17:27
 **/
public class Pixiv {
    public static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new LoggingInterceptor())
            .build();
    /**
     * 根域名，可以修改
     */
    public static String DOMAIN = "https://www.pixiv.net";
    /**
     * php接口 bookmark_add
     */
    public static final String URL_PHP_BOOKMARK_ADD = DOMAIN + "bookmark_add.php";
    /**
     * php接口 rpc_group_setting
     */
    public static final String URL_PHP_RPC_GROUP_SETTING = DOMAIN + "rpc_group_setting.php";


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
