package org.gin.request;

import okhttp3.*;
import org.gin.exception.PixivRequestException;
import org.gin.response.callback.BaseCallback;
import org.gin.response.callback.PixivCallback;
import org.gin.response.convertor.Convertor;
import org.jetbrains.annotations.NotNull;

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

    final Convertor<R> convertor;


    /**
     * POST请求
     * @param httpUrl url
     * @param client  客户端
     * @param body    请求body参数
     * @since 2022/10/15 12:11
     */
    public PixivRequest(HttpUrl httpUrl, OkHttpClient client, Convertor<R> convertor, RequestBody body) {
        this.client = client;
        this.convertor = convertor;
        this.request = createRequest(httpUrl, body);
    }

    /**
     * GET请求
     * @param httpUrl url
     * @param client  客户端
     * @since 2022/10/15 12:11
     */
    public PixivRequest(HttpUrl httpUrl, OkHttpClient client, Convertor<R> convertor) {
        this(httpUrl, client, convertor, null);
    }

    /**
     * 创建请求对象
     * @param httpUrl HttpUrl
     * @param body    body
     * @return okhttp3.Request
     * @since 2022/10/15 17:00
     */
    private static Request createRequest(HttpUrl httpUrl, RequestBody body) {
        final Request.Builder builder = new Request.Builder().url(httpUrl);
        if (body != null) {
            return builder.post(body).build();
        }
//       否则为get请求
        return builder.get().build();
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
    public void async(PixivCallback<R> pixivCallback) {
        async(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                pixivCallback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    final ResponseBody responseBody = BaseCallback.handle(call, response);
                    final R res = convertor.convert(responseBody);
                    pixivCallback.onSuccess(res);
                } catch (PixivRequestException e) {
                    pixivCallback.onPixivException(e);
                } catch (IOException e) {
                    pixivCallback.onFailure(call, e);
                }
            }
        });
    }

    /**
     * 同步请求
     * @return R
     * @throws IOException           异常
     * @throws PixivRequestException pixiv异常
     */
    public R sync() throws PixivRequestException, IOException {
        return convertor.convert(syncBody());
    }

    /**
     * 同步请求
     * @return ResponseBody
     * @throws IOException           异常
     * @throws PixivRequestException pixiv异常
     */
    public ResponseBody syncBody() throws IOException, PixivRequestException {
        final Call call = client.newCall(request);
        final Response response = call.execute();
        return BaseCallback.handle(call, response);
    }
}
