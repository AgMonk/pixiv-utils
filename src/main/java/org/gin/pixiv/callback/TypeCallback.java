package org.gin.pixiv.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Setter;
import org.gin.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 指定返回类型的回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/27 17:54
 */
@Setter
public abstract class TypeCallback<T> extends AbstractCallback {
    Class<T> eClass;

    @Override
    public final void onSuccess(@NotNull String body) {
        try {
            handleResponse(JsonUtils.MAPPER.readValue(body, eClass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理响应
     * @param response 响应
     */
    abstract public void handleResponse(@NotNull T response);
}   
