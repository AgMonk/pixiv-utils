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
public class PixivClientException extends IOException {
    int code;
    Call call;

    public PixivClientException(int code, Call call, String message) {
        super(String.format("客户端错误 code:%d %s", code,message));
        this.code = code;
        this.call = call;
    }
}
