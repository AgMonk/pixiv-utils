package org.gin.pixiv.call;

import okhttp3.Call;
import org.gin.exception.PixivException;
import org.gin.pixiv.callback.TypeCallback;
import org.gin.utils.JsonUtils;

import java.io.IOException;

/**
 * 规定了返回值的Call类
 * @author bx002
 */
public class PixivCallType<T> extends PixivCallString {
    final Class<T> responseClass;

    public PixivCallType(Call call, Class<T> responseClass) {
        super(call);
        this.responseClass = responseClass;
    }

    public void async(TypeCallback<T> callback) {
        callback.setEClass(responseClass);
        this.asyncString(callback);
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