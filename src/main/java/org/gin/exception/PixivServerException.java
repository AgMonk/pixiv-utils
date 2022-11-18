package org.gin.exception;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Call;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 17:54
 **/
@Getter
@Setter
public class PixivServerException extends IOException {
    int code;
    Call call;

    public PixivServerException(int code, String message, Call call) {
        super(String.format("服务器错误 code:%d %s", code, message));
        this.code = code;
        this.call = call;
    }
}
