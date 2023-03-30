package com.gin.pixiv.call;

import com.gin.pixiv.callback.StandardCallback;
import com.gin.pixiv.exception.PixivException;
import com.gin.utils.JsonUtils;
import okhttp3.Call;

import java.io.IOException;

/**
 * 标准返回Call
 * @author bx002
 */
public class PixivCallStandard<T> extends PixivCall<T> {
    public PixivCallStandard(Call call, Class<T> responseClass) {
        super(call, responseClass);
    }

    /**
     * 异步请求
     * @param callback 回调方法
     */
    public void async(StandardCallback<T> callback) {
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
        return JsonUtils.parseRes(s, responseClass);
    }
}   