package org.gin.request;

import okhttp3.HttpUrl;

import java.util.Collection;
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
        return createHttpUrl(null, urlTemplate, args);
    }

    /**
     * 创建URL
     * @param urlTemplate url模板
     * @param queryParam  query查询参数 推荐使用HashMap ，传入null的字段会传递空串
     * @param args        url参数
     * @return HttpUrl
     */
    public static HttpUrl createHttpUrl(Object queryParam, String urlTemplate, Object... args) {
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
            } else {
                builder.addQueryParameter(k, v == null ? "" : v.toString());
            }
        });
        return builder.build();
    }
}
