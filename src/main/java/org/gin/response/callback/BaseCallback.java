package org.gin.response.callback;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.gin.exception.PixivException;
import org.gin.response.PixivResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 15:47
 **/
public interface BaseCallback<R> extends Callback, Convertor<R> {
    /**
     * 处理响应
     * @param call     call
     * @param response 响应
     * @return body
     * @throws PixivException 异常
     * @throws IOException    异常
     */
    static ResponseBody handle(@NotNull Call call, @NotNull Response response) throws PixivException, IOException {
        final int code = response.code();
        final int co = code / 100;
        switch (co) {
            case 3:
            case 2:
                return response.body();
            case 4:
                PixivResponse<Object> res = JSONObject.parseObject(Objects.requireNonNull(response.body()).string(), new TypeReference<>() {
                });
                throw new PixivException(code, res.getMessage(), call);
            case 5:
                throw new PixivException(code, null, call);
            default:
                throw new PixivException(code, "非预期的code", call);
        }
    }

    /**
     * 触发Pixiv异常时的处理方法
     * @param e 异常
     */
    void onPixivException(PixivException e);

    /**
     * 请求成功时的处理方法
     * @param res 返回对象
     */
    void onSuccess(R res);

    /**
     * 失败时的处理方法
     * @param call callback
     * @param e    异常
     */
    @Override
    default void onFailure(@NotNull Call call, @NotNull IOException e) {
        e.printStackTrace();
    }

    /**
     * 请求成功时的处理方法
     * @param call     call
     * @param response 响应
     * @throws IOException 异常
     */
    @Override
    default void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        try {
            final ResponseBody responseBody = handle(call, response);
            final R res = convert(responseBody);
            onSuccess(res);
        } catch (PixivException e) {
            onPixivException(e);
        }
    }
}
