package org.gin.pixiv.call;

import com.fasterxml.jackson.databind.JavaType;
import okhttp3.Call;
import org.gin.exception.PixivException;
import org.gin.pixiv.callback.StandardCallback;
import org.gin.response.PixivResponse;
import org.gin.utils.JsonUtils;

import java.io.IOException;

/**
 * 标准返回Call
 * @author bx002
 */
public class PixivCallStandard<T> extends PixivCallString {
    final Class<T> responseClass;

    public PixivCallStandard(Call call, Class<T> responseClass) {
        super(call);
        this.responseClass = responseClass;
    }

    public void async(StandardCallback<T> callback) {
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
        final JavaType javaType = JsonUtils.MAPPER.getTypeFactory().constructParametricType(PixivResponse.class, responseClass);
        final PixivResponse<T> response = JsonUtils.MAPPER.readValue(s, javaType);
        return response.getBody();
    }
}   