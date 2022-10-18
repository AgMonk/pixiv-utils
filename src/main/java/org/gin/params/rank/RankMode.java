package org.gin.params.rank;

/**
 * @author bx002
 */

public enum RankMode {

    /**
     * 今日
     */
    DAILY,
    /**
     * 本周
     */
    WEEKLY,
    /**
     * 本月
     */
    MONTHLY,
    /**
     * 新人
     */
    ROOKIE,
    ORIGINAL,
    MALE,
    FEMALE,
    DAILY_R18,
    WEEKLY_R18,
    MALE_R18,
    FEMALE_R18;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
