package org.gin.request;

import okhttp3.OkHttpClient;
import org.gin.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:31
 **/
public class PixivCommon {
    public static final String DOMAIN = "https://www.pixiv.net/";
    public static final String DOMAIN_AJAX = DOMAIN + "ajax/";
    public static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new LoggingInterceptor())
            .build();
}
