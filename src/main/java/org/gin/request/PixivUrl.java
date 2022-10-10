package org.gin.request;

import okhttp3.HttpUrl;

import java.util.Objects;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 17:27
 **/
public class PixivUrl {
    public static final String URL_ILLUST_DETAIL = PixivCommon.DOMAIN_AJAX + "illust/%d";
    public static final String URL_FOLLOW_LATEST = PixivCommon.DOMAIN_AJAX + "follow_latest/illust";

    /**
     * 生成查询关注作者最新作品的URL
     * @param page 页码
     * @param mode 模式
     * @param lang 语言
     * @return URL
     */
    public static HttpUrl followLatestUrl(int page, String mode, String lang) {
        return Objects.requireNonNull(HttpUrl.parse(PixivUrl.URL_FOLLOW_LATEST)).newBuilder()
                .addQueryParameter("mode", mode)
                .addQueryParameter("lang", lang)
                .addQueryParameter("p", String.valueOf(page))
                .build();

    }
}
