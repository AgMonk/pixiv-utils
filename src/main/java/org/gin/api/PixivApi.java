package org.gin.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;
import org.gin.api.groups.*;
import org.gin.interceptor.HeaderInterceptor;
import org.gin.interceptor.LangInterceptor;
import org.gin.interceptor.LoggingInterceptor;
import org.gin.request.PixivCookieToken;
import org.jetbrains.annotations.NotNull;

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


//  以下为  API 分组

    private final IllustApi illustApi;
    private final NovelApi novelApi;
    private final NovelSeriesApi novelSeriesApi;
    private final UserApi userApi;
    private final BookmarkApi bookmarkApi;
    private final CommentIllustApi commentIllustApi;
    private final CommentNovelApi commentNovelApi;
    private final RankingApi rankingApi;
    private final UserWorksApi userWorksApi;
    private final UserBookmarkApi userBookmarkApi;
    private final TagApi tagApi;

    private PixivApi(OkHttpClient client, PixivCookieToken cookieToken, String domain) {
        this.client = client;
        this.cookieToken = cookieToken;
        this.domain = domain;


        this.illustApi = new IllustApi(this);
        this.novelApi = new NovelApi(this);
        this.novelSeriesApi = new NovelSeriesApi(this);
        this.userApi = new UserApi(this);
        this.bookmarkApi = new BookmarkApi(this);
        this.commentIllustApi = new CommentIllustApi(this);
        this.commentNovelApi = new CommentNovelApi(this);
        this.rankingApi = new RankingApi(this);
        this.userWorksApi = new UserWorksApi(this);
        this.userBookmarkApi = new UserBookmarkApi(this);
        this.tagApi = new TagApi(this);
    }

    @Setter
    @Accessors(chain = true)
    public static class Builder {
        OkHttpClient client;
        PixivCookieToken cookieToken;
        String domain = "https://www.pixiv.net";
        String lang = "zh";
        boolean log = false;

        @NotNull
        private static OkHttpClient defaultClient() {
            return new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .callTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();
        }

        public PixivApi build() {
            if (cookieToken == null) {
                throw new RuntimeException("必须提供Cookie");
            }

            if (client == null) {
                client = defaultClient();
            }
            final OkHttpClient.Builder builder = client.newBuilder()
                    //添加语言拦截器
                    .addInterceptor(new LangInterceptor(lang))
                    //添加Header拦截器
                    .addInterceptor(new HeaderInterceptor(cookieToken));
            if (log) {
                //日志输出拦截器
                builder.addInterceptor(new LoggingInterceptor());
            }

            client = builder.build();
            return new PixivApi(client, cookieToken, domain);
        }

        public Builder setSessionId(String sessionId) throws IOException {
            this.cookieToken = new PixivCookieToken(sessionId);
            this.cookieToken.findToken(defaultClient(), domain);
            return this;
        }
    }
}
