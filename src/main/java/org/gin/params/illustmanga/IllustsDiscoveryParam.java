package org.gin.params.illustmanga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    String mode = "all";
    Long sampleIllustId;

    public IllustsDiscoveryParam(Long sampleIllustId) {
        this.sampleIllustId = sampleIllustId;
    }
}
