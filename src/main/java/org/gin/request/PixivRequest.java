package org.gin.request;

import okhttp3.*;
import org.gin.exception.PixivException;
import org.gin.response.callback.Callback;
import org.gin.response.convertor.Convertor;

import java.io.IOException;

/**
 * Pixiv请求
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 11:31
 **/
public class PixivRequest<R> {
    final Request request;
    final OkHttpClient client;

    public PixivRequest(HttpUrl httpUrl, OkHttpClient client, PixivCookieToken pixivCookieToken, RequestBody body) {
        this.client = client;
        this.request = PixivCommon.createRequest(pixivCookieToken, httpUrl, body);
    }
    public PixivRequest(HttpUrl httpUrl, OkHttpClient client, PixivCookieToken pixivCookieToken) {
        this.client = client;
        this.request = PixivCommon.createRequest(pixivCookieToken, httpUrl, null);
    }

    /**
     * 异步请求
     * @param callback 响应处理
     */
    public void async(okhttp3.Callback callback) {
        client.newCall(request).enqueue(callback);
    }

    /**
     * 异步请求
     * @param callback 响应处理
     */
    public void async(Callback<R> callback) {
        async((okhttp3.Callback) callback);
    }

    /**
     * 同步请求
     * @return ResponseBody
     * @throws IOException    异常
     * @throws PixivException pixiv异常
     */
    public ResponseBody sync() throws IOException, PixivException {
        final Call call = client.newCall(request);
        final Response response = call.execute();
        return Callback.handle(call, response);
    }

    /**
     * 同步请求
     * @return R
     * @throws IOException    异常
     * @throws PixivException pixiv异常
     */
    public R sync(Convertor<R> convertor) throws PixivException, IOException {
        return convertor.convert(sync());
    }

}