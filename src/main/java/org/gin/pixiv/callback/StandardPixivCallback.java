package org.gin.pixiv.callback;

import org.gin.response.PixivResponse;
import org.jetbrains.annotations.NotNull;

/**
 * Pixiv标准响应回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 14:35
 */
public abstract class StandardPixivCallback<T> extends ClassPixivCallback<PixivResponse<T>> {

    public abstract void onSuccess(T body);

    /**
     * 执行成功回调
     * @param body 响应对象
     */
    @Override
    public void handleResponse(@NotNull PixivResponse<T> body) {
        onSuccess(body.getBody());
    }
}
