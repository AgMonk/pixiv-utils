package com.gin.pixiv.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gin.utils.JsonUtils;

/**
 * 标准响应回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 15:16
 */
public abstract class StandardCallback<T> extends AbstractCallback<T> {
    /**
     * 将字符串解析为指定类型对象
     * @param string body
     * @return 对象
     */
    @Override
    public T parse(String string) throws JsonProcessingException {
        return JsonUtils.parseRes(string, eClass);
    }
}
