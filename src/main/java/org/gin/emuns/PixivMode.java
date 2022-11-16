package org.gin.emuns;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 14:12
 */
public enum PixivMode {
    /**
     * 搜索模式
     */

    ALL("all"),
    SAFE("safe"),
    R18("r18"),

    ;
    final String name;

    @Override
    public String toString() {
        return getName();
    }

    PixivMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}