package org.gin.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Pixiv异常
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 10:58
 **/
@Getter
@Setter
public class PixivException extends Exception {
    int code;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PixivException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PixivException(PixivExceptionEnum pixivExceptionEnum) {
        super(pixivExceptionEnum.getMessage());
        this.code = pixivExceptionEnum.getCode();
    }
}
