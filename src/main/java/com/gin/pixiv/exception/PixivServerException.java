package com.gin.pixiv.exception;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Call;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 17:54
 **/
@Getter
@Setter
public class PixivServerException extends PixivException {
    public PixivServerException(int code, String message, Call call) {
        super(code, "服务器错误: " + message, call);
    }
}
