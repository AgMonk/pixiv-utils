package org.gin.pixiv.response.body.illust;

import lombok.Getter;
import lombok.Setter;
import org.gin.pixiv.enums.IllustType;
import org.gin.pixiv.response.body.common.BaseWorkBody;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;

/**
 * 绘画作品的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/28 12:43
 */
@Getter
@Setter
public class BaseIllustBody extends BaseWorkBody {
    /**
     * 网页标题
     */
    String alt;
    /**
     * 高
     */
    Integer height;
    IllustType illustType;
    /**
     * 上传时间
     */
    @Nullable
    ZonedDateTime uploadDate;
    /**
     * 更新时间
     */
    @Nullable
    ZonedDateTime updateDate;

    /**
     * 宽
     */
    Integer width;
}
