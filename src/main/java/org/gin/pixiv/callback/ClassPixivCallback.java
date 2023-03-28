package org.gin.pixiv.callback;

import lombok.Setter;
import org.gin.response.PixivResponse;
import org.gin.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 指定返回类型的回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/27 17:54
 */
@Setter
public abstract class ClassPixivCallback<T> extends AbstractPixivCallback {
    Class<? extends PixivResponse<T>> eClass;

    @Override
    public final void onSuccess(@NotNull String body) {
        final PixivResponse<T> response = JsonUtils.parse(body, eClass);
        onSuccess(response == null ? null : response.getBody());
    }

    /**
     * 执行成功回调
     * @param body 响应对象
     */
    abstract public void onSuccess(@Nullable T body);
}   
