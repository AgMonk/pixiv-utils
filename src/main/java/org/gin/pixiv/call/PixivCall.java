package org.gin.pixiv.call;

import lombok.RequiredArgsConstructor;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.gin.pixiv.callback.AbstractCallback;
import org.gin.pixiv.exception.PixivException;

import java.io.IOException;

/**
 * 基础Call类
 * @author bx002
 */
@RequiredArgsConstructor
public class PixivCall<T> {
    final Call call;
    final Class<T> responseClass;

    /**
     * 同步请求
     * @return 字符串
     */
    public String syncString() throws IOException, PixivException {
        try (Response response = this.call.execute()) {
            ResponseBody body = AbstractCallback.body(this.call, response);
            return body != null ? body.string() : null;
        }
    }
}