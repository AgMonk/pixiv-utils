package org.gin.request;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 17:27
 **/
public class Pixiv {
    /**
     * 根域名，可以修改
     */
    public static String DOMAIN = "https://www.pixiv.net";
    /**
     * php接口 bookmark_add
     */
    public static final String URL_PHP_BOOKMARK_ADD = DOMAIN + "bookmark_add.php";
    /**
     * php接口 rpc_group_setting
     */
    public static final String URL_PHP_RPC_GROUP_SETTING = DOMAIN + "rpc_group_setting.php";


}
