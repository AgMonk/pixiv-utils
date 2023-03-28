package org.gin.pixiv.call;

import okhttp3.Call;
import org.gin.exception.PixivException;
import org.gin.utils.JsonUtils;

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