package org.gin.pixiv.param;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.gin.emuns.PixivMode;
import org.jetbrains.annotations.NotNull;

/**
 * 请求关注作者的最新作品的参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 12:17
 */
@Getter
@RequiredArgsConstructor
public class LatestParam {
    final int page;
    @NotNull
    final PixivMode mode;
}   
