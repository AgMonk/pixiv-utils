package org.gin.emuns;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 排序规则
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 14:47
 */
public enum PixivOrder {
    /**
     * 从新到旧
     */
    DSC("date_d"),
    /**
     * 从旧到新
     */
    ASC("date"),

    ;
    final String name;

    PixivOrder(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
