package org.gin.exception;

import lombok.Getter;
import lombok.Setter;

import static org.gin.exception.PixivExceptionEnum.URL_CREATE;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 10:59
 **/
@Getter
@Setter
public class PixivUrlCreateException extends PixivException {
    private final String url;
    private final Object[] args;

    public PixivUrlCreateException(String url, Object[] args) {
        super(URL_CREATE);
        this.url = url;
        this.args = args;
    }
}
