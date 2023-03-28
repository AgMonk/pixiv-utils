package org.gin.pixiv.call;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.gin.exception.PixivException;
import org.gin.pixiv.callback.AbstractPixivCallback;

import java.io.IOException;

/**
 * 基础Call类
 * @author bx002
 */
public class PixivCallString {
    final Call call;

    public PixivCallString(Call call) {
        this.call = call;
    }

    /**
     * 异步请求, 回调参数为字符串类型
     * @param callback 回调方法
     */
    public void asyncString(AbstractPixivCallback callback) {
        this.call.enqueue(callback);
    }

    /**
     * 同步请求
     * @return 字符串
     */
    public String syncString() throws IOException, PixivException {
        try (Response response = this.call.execute()) {
            ResponseBody body = AbstractPixivCallback.body(this.call, response);
            return body != null ? body.string() : null;
        }
    }
}