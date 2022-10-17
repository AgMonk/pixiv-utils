package org.gin.response.body.user;

import lombok.Data;

/**
 * 用户信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 10:52
 **/
@Data
public class UserInfoBody {
    /**
     * 是否接受约稿
     */
    Boolean acceptRequest;
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
     * 头像URL
     */
    String image;
    /**
     * 头像URL(大)
     */
    String imageBig;
    /**
     * 自己是否已关注
     */
    Boolean isFollowed;
    /**
     * 昵称
     */
    String name;
    /**
     * 所属地区
     */
    Region region;
    /**
     * 其他社交媒体
     */
    Social social;
    /**
     * 用户id
     */
    Long userId;
    /**
     * 个人主页
     */
    String webpage;
    /**
     * 创作环境
     */
    WorkSpace workspace;

    @Data
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
    @Data
    static class Social {
        SocialUrl pawoo;
        SocialUrl twitter;
    }

    @Data
    static class SocialUrl {
        String url;
    }

    @Data
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
