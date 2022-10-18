package org.gin.params.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/18 10:01
 */
@Getter
@AllArgsConstructor
public enum RankContent {
    /**
     * 排行榜正文
     */

    ALL,
    ILLUST,
    UGOIRA,
    MANGA,
    ;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
