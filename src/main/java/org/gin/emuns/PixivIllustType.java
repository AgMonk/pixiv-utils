package org.gin.emuns;

import lombok.AllArgsConstructor;

/**
 * 作品类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 15:44
 **/
@AllArgsConstructor
public enum PixivIllustType {
    /**
     * 作品类型
     */
    ILLUSTRATION(0,"插画"),
    MANGA(1,"漫画"),
    GIF(2,"动图"),
    ;
    final int id;
    final String name;

}
