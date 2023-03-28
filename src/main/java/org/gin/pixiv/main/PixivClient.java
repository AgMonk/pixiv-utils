package org.gin.pixiv.main;

import lombok.Getter;
import okhttp3.*;
import org.gin.exception.PixivException;
import org.gin.exception.PixivExceptionEnum;
import org.gin.pixiv.call.PixivCall;
import org.gin.pixiv.enums.Headers;
import org.gin.pixiv.enums.Language;
import org.gin.pixiv.enums.Method;
import org.gin.pixiv.enums.ParamType;
import org.gin.utils.JsonUtils;
import org.gin.utils.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pixiv请求客户端
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/27 17:59
 */
public class PixivClient {
    public static final String DEFAULT_HOST = "https://www.pixiv.net";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Pattern PATTERN = Pattern.compile("pixiv.context.token = \"(.+?)\";");
    final OkHttpClient client;
    final String host;
    final Language lang;
    /**
     * 当前用户id
     */
    @Getter
    final long userId;
    /**
     * cookie
     */
    final String cookie;
    /**
     * post 请求使用的令牌
     */
    @Getter
    final String token;
    /**
     * sessionId
     */
    @Getter
    final String phpSessionId;

    public PixivClient(
            @NotNull String phpSessionId
    ) throws PixivException, IOException {
        this(phpSessionId, null, null, null, null);
    }

    public PixivClient(
            @NotNull String phpSessionId,
            @NotNull String token
    ) throws PixivException, IOException {
        this(phpSessionId, null, null, null, token);
    }

    public PixivClient(
            @NotNull String phpSessionId,
            @Nullable Language lang,
            @Nullable String host,
            @Nullable OkHttpClient client,
            @Nullable String token
    ) throws PixivException, IOException {
        this.phpSessionId = phpSessionId;
        this.cookie = "PHPSESSID=" + phpSessionId;
        this.userId = Long.parseLong(phpSessionId.split("_")[0]);
        this.host = host != null ? host : DEFAULT_HOST;
        this.lang = lang != null ? lang : Language.zh;
        /*配置客户端*/
        this.client = client != null ? client : getOkHttpClient();
        /*获取token*/
        this.token = !ObjectUtils.isEmpty(token) ? token : findToken(this.client, this.host, phpSessionId);
    }

    /**
     * 使用请求获取 token
     * @param client       客户端
     * @param host         host
     * @param phpSessionId sessionId
     * @return token
     */
    private static String findToken(OkHttpClient client, String host, String phpSessionId) throws PixivException, IOException {
        final Request request = new Request.Builder().url(host + "/setting_user.php")
                .header(Headers.REFERER, DEFAULT_HOST)
                .header(Headers.COOKIE, "PHPSESSID=" + phpSessionId).build();
        try (final Response response = client.newCall(request).execute()) {
            final String html = Objects.requireNonNull(response.body()).string();
            final Matcher matcher = PATTERN.matcher(html);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        throw new PixivException(PixivExceptionEnum.TOKEN_NOT_FOUND);
    }

    @NotNull
    private static HttpUrl getHttpUrl(@NotNull ParamType type, @NotNull String host, @NotNull String url, @Nullable Object param, Language lang) {
        final HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(host + url)).newBuilder()
                .addQueryParameter("lang", lang.name());

        if (type == ParamType.QUERY) {
            if (param != null) {
                JsonUtils.jsonToMap(param).forEach((k, v) -> {
                    if (v instanceof Collection<?>) {
                        //如果value 是集合，添加多个同名参数
                        ((Collection<?>) v).forEach(i -> urlBuilder.addQueryParameter(k, String.valueOf(i)));
                    } else if (v == null) {
                        //如果value是null 且传入了该值则把null值写成空串
                        urlBuilder.addQueryParameter(k, "");
                    } else {
                        //常规写入
                        urlBuilder.addQueryParameter(k, v.toString());
                    }
                });
            }
        }
        return urlBuilder.build();
    }

    @NotNull
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).build();
    }

    @Nullable
    private static RequestBody getRequestBody(@NotNull ParamType type, @Nullable Object param) {
        if (type == ParamType.QUERY) {
            return null;
        }
        if (type == ParamType.FORM) {
            // 表单body
            final HashMap<String, Object> map = JsonUtils.jsonToMap(param);
            final FormBody.Builder builder = new FormBody.Builder();
            map.forEach((k, v) -> {
                if (v != null) {
                    builder.add(k, String.valueOf(v));
                }
            });
            return builder.build();
        } else {
            // json body
            return RequestBody.create(JsonUtils.obj2Str(param), MEDIA_TYPE_JSON);
        }
    }


    /**
     * 生成Call(原生方法) QUERY方法(GET)
     * @param url   url
     * @param param 参数
     * @return Call
     */
    public Call call(
            @NotNull String url,
            @Nullable Object param
    ) {
        return call(ParamType.QUERY, url, param);
    }

    /**
     * 生成Call(原生方法)
     * @param type  传参方式
     * @param url   url
     * @param param 参数
     * @return Call
     */
    public Call call(
            @NotNull ParamType type,
            @NotNull String url,
            @Nullable Object param
    ) {
        // url
        final HttpUrl httpUrl = getHttpUrl(type, this.host, url, param, lang);
        // 请求体
        final RequestBody body = getRequestBody(type, param);
        // 请求方法
        final Method method = type == ParamType.QUERY ? Method.GET : Method.POST;
        // 请求Builder
        final Request.Builder builder = new Request.Builder().url(httpUrl)
                .header(Headers.REFERER, DEFAULT_HOST)
                .header(Headers.COOKIE, this.cookie)
                .method(method.name(), body);
        if (method == Method.POST) {
            //POST 请求 ，写入 token
            builder.header(Headers.CSRF_TOKEN, this.token);
        }
        return this.client.newCall(builder.build());
    }

    /**
     * 生成call(带响应类型的) QUERY方法(GET)
     * @param url           url
     * @param param         参数
     * @param responseClass 响应类型
     * @return Call
     */
    public <T> PixivCall<T> call(
            @NotNull String url,
            @Nullable Object param,
            Class<T> responseClass
    ) {
        return call(ParamType.QUERY, url, param, responseClass);
    }

    /**
     * 生成call(带响应类型的)
     * @param type          传参方式
     * @param url           url
     * @param param         参数
     * @param responseClass 响应类型
     * @return Call
     */
    public <T> PixivCall<T> call(
            @NotNull ParamType type,
            @NotNull String url,
            @Nullable Object param,
            Class<T> responseClass
    ) {
        return new PixivCall<>(call(type, url, param), responseClass);
    }
}
