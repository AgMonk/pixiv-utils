package org.gin.params.illustmanga;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gin.emuns.PixivMode;
import org.gin.emuns.PixivOrder;

/**
 * 插画/漫画搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IllustMangaSearchParam {
    /**
     * 页码
     */
    @JSONField(name = "p")
    int page = 1;
    /**
     * 排序模式
     */
    PixivOrder order = PixivOrder.DSC;
    /**
     * 模式
     */
    PixivMode mode = PixivMode.ALL;

    /**
     * 发布时间（起），以东九区为准，格式：yyyy-MM-dd
     */
    @JSONField(name = "scd")
    String dateStart;

    /**
     * 发布时间（止），以东九区为准，格式：yyyy-MM-dd
     */
    @JSONField(name = "ecd")
    String dateEnd;

}
