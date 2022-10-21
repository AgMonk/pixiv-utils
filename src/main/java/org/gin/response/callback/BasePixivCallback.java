package org.gin.response.callback;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.gin.exception.PixivClientException;
import org.gin.exception.PixivServerException;
import org.gin.response.PixivResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.ZonedDateTime;
import java.util.Objects;

import static org.gin.interceptor.LoggingInterceptor.DATE_TIME_FORMATTER;

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
     * 处理响应
     * @param call     call
     * @param response 响应
     * @return body
     * @throws PixivClientException 客户端错误
     * @throws PixivServerException 服务器错误
     * @throws IOException          异常
     */
    static ResponseBody handle(@NotNull Call call, @NotNull Response response) throws IOException {
        final int code = response.code();
        final int co = code / 100;
        switch (co) {
            case 3:
            case 2:
                return response.body();
            case 4:
                final String string = Objects.requireNonNull(response.body()).string();
                PixivResponse<Object> res = JSONObject.parseObject(string, new TypeReference<PixivResponse<Object>>() {
                });
                throw new PixivClientException(code, call, res.getMessage());
            case 5:
                throw new PixivServerException(code, call);
            default:
                System.err.printf("非预期的code:%d", code);
                throw new PixivClientException(code, call, null);
        }
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
        try {
            onSuccess(handle(call, response));
        } catch (PixivClientException e) {
            onClientError(e.getCall(), e.getCode(), e.getMessage());
        } catch (PixivServerException e) {
            onServerError(e.getCall(), e.getCode());
        }
    }

    /**
     * 发生400+错误 (客户端错误) 时的处理方法
     * @param call    call
     * @param code    响应code
     * @param message 响应body
     * @throws IOException 异常
     */
    default void onClientError(Call call, int code, String message) throws IOException {
        if (message == null) {
            return;
        }
        System.err.printf("%s [DEBUG]%s %s \n",
                DATE_TIME_FORMATTER.format(ZonedDateTime.now()),
                message,
                call.request().url()
        );
    }

    /**
     * 发生500+错误 (服务器错误) 时的处理方法
     * @param call call
     * @param code 响应code
     */
    default void onServerError(Call call, int code) {
        System.err.printf("%s [DEBUG]服务器错误 %s code:%d \n",
                DATE_TIME_FORMATTER.format(ZonedDateTime.now()),
                call.request().url(),
                code);
    }
}
