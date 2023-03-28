package org.gin.pixiv.call;

import com.fasterxml.jackson.databind.JavaType;
import okhttp3.Call;
import org.gin.exception.PixivException;
import org.gin.pixiv.callback.ClassPixivCallback;
import org.gin.response.PixivResponse;
import org.gin.utils.JsonUtils;

import java.io.IOException;

/**
 * 规定了返回值的Call类
 * @author bx002
 */
public class PixivCall<T> extends PixivCallString {
    final Class<T> responseClass;

    public PixivCall(Call call, Class<T> responseClass) {
        super(call);
        this.responseClass = responseClass;
    }

    public void async(ClassPixivCallback<T> callback) {
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
        JavaType javaType = JsonUtils.MAPPER.getTypeFactory().constructParametricType(PixivResponse.class, responseClass);
        final PixivResponse<T> res = JsonUtils.MAPPER.readValue(s, javaType);
        return res == null ? null : res.getBody();
    }
}   