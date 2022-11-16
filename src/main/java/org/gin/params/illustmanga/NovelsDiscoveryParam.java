package org.gin.params.illustmanga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gin.emuns.PixivMode;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/21 09:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovelsDiscoveryParam {
    Integer limit = 20;
    /**
     * 模式
     */
    PixivMode mode = PixivMode.ALL;
    Long sampleNovelId;
}
