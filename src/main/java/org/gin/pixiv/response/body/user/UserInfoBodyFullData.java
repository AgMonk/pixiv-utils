package org.gin.pixiv.response.body.user;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户完整信息字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/30 09:57
 */
@Getter
@Setter
public class UserInfoBodyFullData {
    /**
     * 是否可以私信
     */
    Boolean canSendMessage;
    /**
     * 个人简介(备注)
     */
    String comment;
    /**
     * 个人简介(备注) HTML格式
     */
    String commentHtml;
    /**
     * 是否回关
     */
    Boolean followedBack;
    /**
     * ta关注的人数
     */
    Integer following;
    /**
     * 所属地区
     */
    Region region;
    /**
     * 其他社交媒体
     */
    Social social;
    /**
     * 个人主页
     */
    String webpage;
    /**
     * 创作环境
     */
    WorkSpace workspace;

    @Getter
    @Setter
    static class Region {
        String name;
        /**
         * 地方行政区域
         */
        String prefecture;
        String privacyLevel;
        String region;
    }

    /**
     * 其他社交媒体
     */
    @Getter
    @Setter
    static class Social {
        SocialUrl pawoo;
        SocialUrl twitter;
    }

    @Getter
    @Setter
    static class SocialUrl {
        String url;
    }

    @Getter
    @Setter
    static class WorkSpace {
        /**
         * 椅子
         */
        String userWorkspaceChair;
        /**
         * 桌面大小
         */
        String userWorkspaceDesk;
        /**
         * 桌面物品
         */
        String userWorkspaceDesktop;
        /**
         * 显示器
         */
        String userWorkspaceMonitor;
        /**
         * 操作系统
         */
        String userWorkspacePc;
        /**
         * 数位板
         */
        String userWorkspaceTablet;
        /**
         * 软件
         */
        String userWorkspaceTool;
    }
}   
