package org.gin.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixivParamSearch {
    /**
     * 排序模式: 可选值：`date_d`从新到旧(默认),`date`从旧到新
     */
    String order = "date_d";
    /**
     * 模式 可选值：`all`、`safe`、`r18`
     */
    String mode = "all";
    /**
     * 语言
     */
    String lang = "zh";
    /**
     * 发布时间（起），以东九区为准，格式：yyyy-MM-dd
     */
    String scd;
    /**
     * 发布时间（止），以东九区为准，格式：yyyy-MM-dd
     */
    String ecd;


}