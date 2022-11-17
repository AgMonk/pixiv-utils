package org.gin.interceptor;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.gin.request.PixivCookieToken;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Header拦截器
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 14:34
 **/
@RequiredArgsConstructor
public class HeaderInterceptor implements Interceptor {

    public static final String POST = "post";
    private final PixivCookieToken cookieToken;

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();

        //写入 referer和cookie
        final Request.Builder builder = original.newBuilder()
                .header("referer", "https://www.pixiv.net/")
                .header("cookie", cookieToken.getCookie());
        if (POST.equalsIgnoreCase(original.method())) {
            //POST 请求 ，写入 token
            builder.header("x-csrf-token", cookieToken.getToken());
        }

        return chain.proceed(builder.build());
    }
}
