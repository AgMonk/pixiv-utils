package org.gin.pixiv.callback;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.gin.exception.PixivClientException;
import org.gin.exception.PixivException;
import org.gin.exception.PixivServerException;
import org.gin.response.PixivResponse;
import org.gin.response.convertor.Convertor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 抽象回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/27 17:44
 */
public abstract class AbstractPixivCallback implements Callback {
    public static ResponseBody body(@NotNull Call call, @NotNull Response response) throws IOException, PixivException {
        final int code = response.code();
        final int co = code / 100;
        final ResponseBody body = response.body();
        switch (co) {
            case 3:
            case 2:
                return body;
            case 4:
                if (body == null) {
                    throw new PixivClientException(code, "", call);
                }
                PixivResponse<Void> res = Convertor.toVoid(body);
                throw new PixivClientException(code, res.getMessage(), call);
            case 5:
                throw new PixivServerException(code, "服务器异常", call);
            default:
                throw new PixivClientException(code, "非预期的code", call);
        }
    }

    @Override
    public final void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        try {
            try (ResponseBody body = body(call, response)) {
                if (body != null) {
                    onSuccess(body.string());
                }
            }
        } catch (PixivException e) {
            handleException(e);
        }
    }

    /**
     * 执行成功回调
     * @param body body字符串
     */
    public abstract void onSuccess(@NotNull String body);

    /**
     * 处理异常
     * @param e 异常
     */
    public void handleException(PixivException e) {
        e.printStackTrace();
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        e.printStackTrace();
    }
}   
