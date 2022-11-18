package org.gin.response.callback;

import okhttp3.Call;
import org.gin.exception.PixivException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/18 11:14
 */
public interface PixivCallback<R> {

    /**
     * 请求成功时的处理方法
     * @param res 返回对象
     * @throws PixivException 异常
     * @throws IOException    异常
     */
    void onSuccess(R res) throws PixivException, IOException;

    /**
     * 请求失败的处理方法
     * @param call call
     * @param e    异常
     */
    default void onFailure(@NotNull Call call, @NotNull IOException e) {
        e.printStackTrace();
    }

    /**
     * 触发Pixiv异常时的处理方法
     * @param e 异常
     */
    default void onPixivException(PixivException e) {
        e.printStackTrace();
    }
}
