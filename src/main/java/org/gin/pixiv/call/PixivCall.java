package org.gin.pixiv.call;

import lombok.RequiredArgsConstructor;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.gin.exception.PixivException;
import org.gin.pixiv.callback.AbstractCallback;

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
     * 异步请求
     * @param callback 回调方法
     */
    public void async(AbstractCallback<T> callback) {
        callback.setEClass(responseClass);
        this.call.enqueue(callback);
    }

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