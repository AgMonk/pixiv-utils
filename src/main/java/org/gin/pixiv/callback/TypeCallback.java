package org.gin.pixiv.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.gin.utils.JsonUtils;

/**
 * 指定返回类型的回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/27 17:54
 */
public abstract class TypeCallback<T> extends AbstractCallback<T> {
    /**
     * 将字符串解析为指定类型对象
     * @param string body
     * @return 对象
     */
    @Override
    public T parse(String string) throws JsonProcessingException {
        if (eClass == String.class) {
            //noinspection unchecked
            return (T) string;
        }
        return JsonUtils.parse(string, eClass);
    }
}
