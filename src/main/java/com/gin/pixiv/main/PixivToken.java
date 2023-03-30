package com.gin.pixiv.main;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 访问令牌
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 10:12
 */
@Getter
@RequiredArgsConstructor
public class PixivToken {
    final long userId;
    final String cookie;
    @Setter
    String token;

    public PixivToken(@NotNull String phpSessionId) {
        this(phpSessionId, null);
    }

    public PixivToken(@NotNull String phpSessionId, @Nullable String token) {
        this.cookie = "PHPSESSID=" + phpSessionId;
        this.userId = Long.parseLong(phpSessionId.split("_")[0]);
        this.token = token;
    }
}
