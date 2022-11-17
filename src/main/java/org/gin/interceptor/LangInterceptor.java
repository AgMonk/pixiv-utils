package org.gin.interceptor;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 语言拦截器
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:34
 **/
public class LangInterceptor implements Interceptor {

    private final String lang;

    public LangInterceptor(String lang) {
        this.lang = lang;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        final HttpUrl httpUrl = chain.request().url().newBuilder()
                .addQueryParameter("lang", lang)
                .build();
        final Request request = chain.request().newBuilder()
                .url(httpUrl)
                .build();
        return chain.proceed(request);
    }
}
