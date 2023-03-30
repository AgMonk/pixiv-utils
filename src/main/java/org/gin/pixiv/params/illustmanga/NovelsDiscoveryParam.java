package org.gin.pixiv.params.illustmanga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gin.pixiv.enums.PixivMode;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/21 09:13
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NovelsDiscoveryParam {
    Integer limit = 20;
    /**
     * 模式
     */
    PixivMode mode = PixivMode.all;
    Long sampleNovelId;

    public NovelsDiscoveryParam(Long sampleNovelId) {
        this.sampleNovelId = sampleNovelId;
    }
}
