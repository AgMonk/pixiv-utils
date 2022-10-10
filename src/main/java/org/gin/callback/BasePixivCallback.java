package org.gin.callback;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.gin.TimeUtils;
import org.gin.response.PixivResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.ZonedDateTime;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 16:31
 **/
public interface BasePixivCallback extends Callback {
    /**
     * 请求成功时的处理方法
     * @param responseBody 响应body
     * @throws IOException 异常
     */
    void onSuccess(ResponseBody responseBody) throws IOException;

    /**
     * 发生400+错误 (客户端错误) 时的处理方法
     * @param call     call
     * @param code         响应code
     * @param responseBody 响应body
     * @throws IOException 异常
     */
    default void onClientError(Call call, int code, ResponseBody responseBody) throws IOException {
        if (responseBody == null) {
            return;
        }
        final String string = responseBody.string();
        PixivResponse<Object> response = JSONObject.parseObject(string, new TypeReference<>() {
        });
        System.err.printf("%s [DEBUG]客户端错误: %s code:%d %s \n",
                TimeUtils.format(ZonedDateTime.now()),
                call.request().url(),
                code,
                response.getMessage());
    }

    /**
     * 请求失败时的处理方法
     * @param call call
     * @param e    异常
     */
    @Override
    default void onFailure(@NotNull Call call, @NotNull IOException e) {
        if (e instanceof SocketTimeoutException) {
            final String message = e.getMessage();
            System.err.println(message);
        } else {
            e.printStackTrace();
        }
    }

    /**
     * 获得响应时的处理方法
     * @param call     call
     * @param response 响应
     * @throws IOException 异常
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    default void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        final int code = response.code();
        if (code >= 200 && code < 300) {
            onSuccess(response.body());
        } else if (code >= 400 && code < 500) {
            onClientError(call, code, response.body());
        } else if (code >= 500 && code < 600) {
            onServerError(call, code);
        } else {
            System.err.printf("非预期的code:%d", code);
        }
    }

    /**
     * 发生500+错误 (服务器错误) 时的处理方法
     * @param call     call
     * @param code 响应code
     */
    default void onServerError(Call call, int code) {
        System.err.printf("%s [DEBUG]服务器错误 %s code:%d \n",
                TimeUtils.format(ZonedDateTime.now()) ,
                call.request().url(),
                code);
    }
}
