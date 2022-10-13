package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.gin.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:31
 **/
@SuppressWarnings("unused")
public class PixivCommon {
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public static final String DOMAIN = "https://www.pixiv.net/";
    public static final String DOMAIN_AJAX = DOMAIN + "ajax/";
    public static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new LoggingInterceptor())
            .build();

    public static Request createGetRequest(String cookie, String url) {
        return new Request.Builder()
                .url(url)
                .header("cookie", cookie)
                .header("referer", "https://www.pixiv.net/")
                .build();
    }

    public static Request createGetRequest(String cookie, HttpUrl url) {
        return new Request.Builder()
                .url(url)
                .header("cookie", cookie)
                .header("referer", "https://www.pixiv.net/")
                .build();
    }

    public static Request createPostRequest(String cookie, HttpUrl url, String token, RequestBody body) {
        return new Request.Builder()
                .url(url)
                .header("cookie", cookie)
                .header("x-csrf-token", token)
                .header("referer", "https://www.pixiv.net/")
                .post(body)
                .build()
                ;
    }

    public static Request createPostRequest(String cookie, String url, String token, RequestBody body) {
        return new Request.Builder()
                .url(url)
                .header("cookie", cookie)
                .header("x-csrf-token", token)
                .header("referer", "https://www.pixiv.net/")
                .post(body)
                .build()
                ;
    }

    public static RequestBody createJsonBody(Object obj) {
        return RequestBody.create(JSONObject.toJSONString(obj), PixivCommon.MEDIA_TYPE_JSON);
    }
}
