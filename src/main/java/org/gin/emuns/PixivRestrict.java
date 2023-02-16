package org.gin.emuns;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 是否为公开收藏 / 关注
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/18 15:46
 */
public enum PixivRestrict {
    /**
     * 公开
     */
    TRUE(0),
    /**
     * 非公开
     */
    FALSE(1),
    ;
    final int id;

    PixivRestrict(int id) {
        this.id = id;
    }

    @JsonValue
    public int getId() {
        return id;
    }
}

