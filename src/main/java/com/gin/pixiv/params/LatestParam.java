package com.gin.pixiv.params;

import com.gin.pixiv.enums.PixivMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * 请求关注作者的最新作品的参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 12:17
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LatestParam {
    int page = 1;
    @NotNull
    PixivMode mode = PixivMode.all;
}   
