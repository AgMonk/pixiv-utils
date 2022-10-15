package org.gin.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.*;
import org.gin.StringUtils;
import org.gin.exception.PixivUrlCreateException;
import org.gin.interceptor.LoggingInterceptor;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:31
 **/

public class PixivCommon {
    public static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new LoggingInterceptor())
            .build();
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public static Request createGetRequest(String cookie, String url) {
        return createRequest(cookie, url, null, null);
    }

    public static Request createGetRequest(String cookie, HttpUrl url) {
        return createRequest(cookie, url, null, null);
    }

    public static Request createPostRequest(String cookie, HttpUrl url, String token, RequestBody body) {
        return createRequest(cookie, url, token, body);
    }

    public static Request createPostRequest(String cookie, String url, String token, RequestBody body) {
        return createRequest(cookie, url, token, body);
    }

    /**
     * 创建请求对象
     * @param cookie cookie
     * @param url    url
     * @param token  token
     * @param body   body
     * @return okhttp3.Request
     */
    public static Request createRequest(String cookie, String url, String token, RequestBody body) {
        return createRequest(cookie, HttpUrl.parse(url), token, body);
    }
    public static Request createRequest( PixivCookieToken pixivCookieToken, HttpUrl httpUrl, RequestBody body) {
        return createRequest(pixivCookieToken.getCookie(),httpUrl,pixivCookieToken.getToken(),body);
    }

    /**
     * 创建请求对象
     * @param cookie  cookie
     * @param httpUrl url
     * @param token   token
     * @param body    body
     * @return okhttp3.Request
     */
    public static Request createRequest(String cookie, HttpUrl httpUrl, String token, RequestBody body) {
        final Request.Builder builder = new Request.Builder().url(httpUrl)
                .header("referer", "https://www.pixiv.net/")
                .header("cookie", cookie);
//        如果传入了token 添加token header
        if (StringUtils.isNotEmpty(token)) {
            builder.header("x-csrf-token", token);
        }
        //有 body 表示为post请求
        if (body != null) {
            return builder.post(body).build();
        }
//       否则为get请求
        return builder.get().build();
    }

    /**
     * 创建URL
     * @param urlTemplate url模板
     * @param args        url参数
     * @return HttpUrl
     */
    public static HttpUrl createHttpUrl(String urlTemplate, Object... args) throws PixivUrlCreateException {
        return createHttpUrl(null, urlTemplate, args);
    }

    /**
     * 创建URL
     * @param urlTemplate url模板
     * @param queryParam  query查询参数 推荐使用HashMap ，传入null的字段会传递空串
     * @param args        url参数
     * @return HttpUrl
     */
    public static HttpUrl createHttpUrl(Object queryParam, String urlTemplate, Object... args) throws PixivUrlCreateException {
        final String url = String.format(urlTemplate, args);
        final HttpUrl httpUrl = HttpUrl.parse(url);
        //解析url失败 抛出异常
        if (httpUrl == null) {
            throw new PixivUrlCreateException(url, args);
        }
        //如果没有查询条件，直接返回url
        if (queryParam == null) {
            return httpUrl;
        }
        //添加查询条件
        final HttpUrl.Builder builder = httpUrl.newBuilder();
        final HashMap<String, Serializable> map = jsonToMap(queryParam);
        map.forEach((k, v) -> {
            if (v instanceof Collection<?>) {
                ((Collection<?>) v).forEach(i -> {
                    builder.addQueryParameter(k, String.valueOf(i));
                });
            } else {
                builder.addQueryParameter(k, v == null ? "" : v.toString());
            }
        });
        return builder.build();
    }

    /**
     * 创建 RequestBody
     * @param obj 对象 推荐使用HashMap ，传入null的字段会传递空串
     * @return RequestBody
     */
    public static RequestBody createJsonBody(Object obj) {
        return RequestBody.create(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue), PixivCommon.MEDIA_TYPE_JSON);
    }
    /**
     * 创建 FormBody
     * @param obj 对象 推荐使用HashMap ，传入null的字段会传递空串
     * @return FormBody
     */
    public static FormBody createFormBody(Object obj){
        final HashMap<String, Serializable> map = jsonToMap(obj);
        final FormBody.Builder builder = new FormBody.Builder();
        map.forEach((k, v) -> builder.add(k, v == null ? "" : String.valueOf(v)));
        return builder.build();
    }

    /**
     * 把对象转换为HashMap
     * @param obj 对象 推荐使用HashMap ，传入null的字段会传递空串
     * @return HashMap
     */
    public static HashMap<String, Serializable> jsonToMap(Object obj) {
        return JSONObject.parseObject(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue), new TypeReference<>() {
        });
    }
}
