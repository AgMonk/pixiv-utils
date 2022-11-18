package org.gin.exception;

import okhttp3.Call;

/**
 * Pixiv请求异常
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/15 11:28
 */
public class PixivRequestException extends PixivException {
    /**
     * Constructs an {@code IOException} with the specified detail message.
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public PixivRequestException(int code, String message, Call call) {
        super(code, message, call);
    }
}
