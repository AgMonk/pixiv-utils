package org.gin.params;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gin.emuns.PixivMode;
import org.gin.emuns.PixivOrder;

/**
 * 插画/漫画搜索参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/12 17:17
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseSearchParam {
    /**
     * 页码
     */
    @JsonProperty("p")
    int page = 1;
    /**
     * 排序模式
     */
    PixivOrder order = PixivOrder.DSC;
    /**
     * 模式
     */
    PixivMode mode = PixivMode.all;
    /**
     * 发布时间（起），以东九区为准，格式：yyyy-MM-dd
     */
    @JsonProperty("scd")
    String dateStart;
    /**
     * 发布时间（止），以东九区为准，格式：yyyy-MM-dd
     */
    @JsonProperty("ecd")
    String dateEnd;

    public BaseSearchParam(int page) {
        this.page = page;
    }
}
