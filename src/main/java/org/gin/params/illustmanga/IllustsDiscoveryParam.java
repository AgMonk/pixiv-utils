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
public class IllustsDiscoveryParam {
    Integer limit = 20;
    PixivMode mode = PixivMode.all;
    Long sampleIllustId;

    public IllustsDiscoveryParam(Long sampleIllustId) {
        this.sampleIllustId = sampleIllustId;
    }
}
