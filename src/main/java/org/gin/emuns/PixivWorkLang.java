package org.gin.emuns;

/**
 * 小说的写作语言
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/16 14:43
 */
public enum PixivWorkLang {
    /**
     * 简中
     */
    ZH_CN("zh-cn"),
    /**
     * 繁中
     */
    ZH_TW("zh-tw"),
    /**
     * 英文
     */
    EN("en"),
    /**
     * 日文
     */
    JP("ja"),
    ;
    final String name;

    PixivWorkLang(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}