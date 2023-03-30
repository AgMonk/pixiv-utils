package org.gin.pixiv.call;

import okhttp3.Call;
import org.gin.pixiv.callback.TypeCallback;
import org.gin.pixiv.exception.PixivException;
import org.gin.utils.JsonUtils;

import java.io.IOException;

/**
 * 规定了返回值的Call类
 * @author bx002
 */
public class PixivCallType<T> extends PixivCall<T> {

    public PixivCallType(Call call, Class<T> responseClass) {
        super(call, responseClass);
    }

    /**
     * 异步请求
     * @param callback 回调方法
     */
    public void async(TypeCallback<T> callback) {
        callback.setEClass(responseClass);
        this.call.enqueue(callback);
    }

    /**
     * 同步请求
     * @return 响应
     */
    public T sync() throws IOException, PixivException {
        final String s = this.syncString();
        if (s == null) {
            return null;
        }
        return JsonUtils.MAPPER.readValue(s, responseClass);
    }
}   