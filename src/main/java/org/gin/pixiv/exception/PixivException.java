package org.gin.pixiv.exception;

import lombok.Getter;
import okhttp3.Call;

import java.io.IOException;

/**
 * Pixiv异常
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 10:58
 **/
@Getter
public class PixivException extends IOException {
    final int code;
    final Call call;

    /**
     * Constructs an {@code IOException} with the specified detail message.
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public PixivException(int code, String message, Call call) {
        super(message);
        this.code = code;
        this.call = call;
    }

    public PixivException(PixivExceptionEnum item) {
        super(item.message);
        this.code = item.code;
        this.call = null;
    }
}
