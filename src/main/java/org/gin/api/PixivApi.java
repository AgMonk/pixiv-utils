package org.gin.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;
import org.gin.api.groups.IllustApi;
import org.gin.api.groups.NovelApi;
import org.gin.api.groups.NovelSeriesApi;
import org.gin.api.groups.UserApi;
import org.gin.exception.PixivException;
import org.gin.interceptor.LangInterceptor;
import org.gin.interceptor.LoggingInterceptor;
import org.gin.request.PixivCookieToken;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * API总入口
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 09:33
 */
@Getter
public class PixivApi {
    private final OkHttpClient client;
    private final PixivCookieToken cookieToken;
    private final String domain;
    private final String lang;


//  以下为  API 分组

    private final IllustApi illustApi;
    private final NovelApi novelApi;
    private final NovelSeriesApi novelSeriesApi;
    private final UserApi userApi;

    private PixivApi(OkHttpClient client, PixivCookieToken cookieToken, String domain, String lang) {
        this.client = client;
        this.cookieToken = cookieToken;
        this.domain = domain;
        this.lang = lang;

//        todo new
        this.illustApi = new IllustApi(this);
        this.novelApi = new NovelApi(this);
        this.novelSeriesApi = new NovelSeriesApi(this);
        this.userApi = new UserApi(this);
    }

    @Setter
    @Accessors(chain = true)
    public static class Builder {
        OkHttpClient client;
        PixivCookieToken cookieToken;
        String domain = "https://www.pixiv.net";
        String lang = "zh";

        public PixivApi build() {
            if (cookieToken == null) {
                throw new RuntimeException("必须提供Cookie");
            }

            if (client == null) {
                client = new OkHttpClient.Builder()
                        .readTimeout(30, TimeUnit.SECONDS)
                        .callTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .addInterceptor(new LangInterceptor(lang))
                        .addInterceptor(new LoggingInterceptor())
                        .build();
            } else {
                //添加语言拦截器
                client = client.newBuilder().addInterceptor(new LangInterceptor(lang)).build();
            }

            return new PixivApi(client, cookieToken, domain, lang);
        }

        public Builder setSessionId(String sessionId) throws PixivException, IOException {
            this.cookieToken = new PixivCookieToken(sessionId);
            this.cookieToken.findToken(client, domain);
            return this;
        }
    }
}
