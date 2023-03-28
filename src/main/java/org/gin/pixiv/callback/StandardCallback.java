package org.gin.pixiv.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import lombok.Setter;
import org.gin.response.PixivResponse;
import org.gin.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 标准返回对象
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 15:16
 */
@Setter
public abstract class StandardCallback<T> extends AbstractCallback {
    Class<T> eClass;

    /**
     * 处理响应
     * @param body 响应
     */
    abstract public void handleBody(@NotNull T body);

    /**
     * 执行成功回调
     * @param body body字符串
     */
    @Override
    public void onSuccess(@NotNull String body) {
        try {
            final JavaType javaType = JsonUtils.MAPPER.getTypeFactory().constructParametricType(PixivResponse.class, eClass);
            final PixivResponse<T> response = JsonUtils.MAPPER.readValue(body, javaType);
            handleBody(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
