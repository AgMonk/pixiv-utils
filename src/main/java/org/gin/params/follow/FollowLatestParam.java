package org.gin.params.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询关注作者最新作品参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 16:48
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowLatestParam {
    int page = 1;
    /**
     * 可选值: all r18
     */
    String mode = "all";
    /**
     * 语言
     */
    String lang = "zh";

    public FollowLatestParam(int page) {
        this.page = page;
    }
}
