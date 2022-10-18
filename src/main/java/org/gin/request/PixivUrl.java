package org.gin.request;

import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.HttpUrl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * HttpUrl工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 15:21
 */
public class PixivUrl {
    /**
     * 创建URL
     * @param urlTemplate url模板
     * @param args        url参数
     * @return HttpUrl
     */
    public static HttpUrl createHttpUrl(String urlTemplate, Object... args) {
        return createHttpUrl(null, Collections.singleton(SerializerFeature.WriteMapNullValue), urlTemplate, args);
    }

    /**
     * 创建URL
     * @param queryParam  query查询参数 推荐使用HashMap ，传入null的字段会传递空串
     * @param urlTemplate url模板
     * @param args        url参数
     * @return HttpUrl
     */
    public static HttpUrl createHttpUrl(Object queryParam, Collection<SerializerFeature> features, String urlTemplate, Object... args) {
        final String url = String.format(urlTemplate, args);
        final HttpUrl httpUrl = HttpUrl.parse(url);
        //解析url失败 抛出异常
//        if (httpUrl == null) {
//            throw new PixivUrlCreateException(url, args);
//        }
        assert httpUrl != null;
        //如果没有查询条件，直接返回url
        if (queryParam == null) {
            return httpUrl;
        }
        //添加查询条件
        final HttpUrl.Builder builder = httpUrl.newBuilder();
        final HashMap<String, Object> map = Pixiv.jsonToMap(queryParam);
        map.forEach((k, v) -> {
            if (v instanceof Collection<?>) {
                ((Collection<?>) v).forEach(i -> {
                    builder.addQueryParameter(k, String.valueOf(i));
                });
            } else if (v == null) {
                //如果传入了该值则把null值写成空串
                if (features != null && features.contains(SerializerFeature.WriteMapNullValue)) {
                    builder.addQueryParameter(k, "");
                }
            } else {
                builder.addQueryParameter(k, v.toString());
            }
        });
        return builder.build();
    }
}
