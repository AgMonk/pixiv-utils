package org.gin.request;

import okhttp3.*;
import org.gin.exception.PixivException;
import org.gin.exception.PixivRequestException;
import org.gin.response.callback.BaseCallback;
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
/**
 * POST请求
 * @param httpUrl          url
 * @param client           客户端
 * @param pixivCookieToken cooke和token
 * @param body             请求body参数
 * @return PixivRequest
 * @since 2022/10/15 12:11
 */
public PixivRequest(HttpUrl httpUrl, OkHttpClient client, PixivCookieToken pixivCookieToken, RequestBody body) {
    this.client = client;
    this.request = PixivCommon.createRequest(pixivCookieToken, httpUrl, body);
}

    /**
     * GET请求
     * @param httpUrl          url
     * @param client           客户端
     * @param pixivCookieToken cooke和token
     * @return PixivRequest
     * @since 2022/10/15 12:11
     */
    public PixivRequest(HttpUrl httpUrl, OkHttpClient client, PixivCookieToken pixivCookieToken) {
        this.client = client;
        this.request = PixivCommon.createRequest(pixivCookieToken, httpUrl, null);
    }

    /**
     * 异步请求
     * @param callback 响应处理
     */
    public void async(Callback callback) {
        client.newCall(request).enqueue(callback);
    }

    /**
     * 异步请求
     * @param baseCallback 响应处理
     */
    public void async(BaseCallback<R> baseCallback) {
        async((Callback) baseCallback);
    }

    /**
     * 同步请求
     * @return ResponseBody
     * @throws IOException    异常
     * @throws PixivException pixiv异常
     */
    public ResponseBody sync() throws IOException, PixivRequestException {
        final Call call = client.newCall(request);
        final Response response = call.execute();
        return BaseCallback.handle(call, response);
    }

    /**
     * 同步请求
     * @return R
     * @throws IOException    异常
     * @throws PixivException pixiv异常
     */
    public R sync(Convertor<R> convertor) throws PixivRequestException, IOException {
        return convertor.convert(sync());
    }

}
