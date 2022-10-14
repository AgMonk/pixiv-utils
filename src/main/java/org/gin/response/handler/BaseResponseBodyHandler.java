package org.gin.response.handler;

import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * ResponseBody处理
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 12:00
 **/
public interface BaseResponseBodyHandler<R> {
    /**
     * 把 ResponseBody 转换为指定类型的方法
     * @throws IOException 异常
     * @param body body
     * @return R
     */
    R convert(ResponseBody body) throws IOException;
}
