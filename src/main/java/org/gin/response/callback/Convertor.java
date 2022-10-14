package org.gin.response.callback;

import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 16:00
 **/
public interface Convertor<R> {
    /**
     * 从 ResponseBody 转换到R的方法
     * @param responseBody ResponseBody
     * @throws IOException 异常
     * @return R
     */
    R convert(ResponseBody responseBody) throws IOException;
}
