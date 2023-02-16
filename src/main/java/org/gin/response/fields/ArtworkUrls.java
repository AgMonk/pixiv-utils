package org.gin.response.fields;

import lombok.Data;

/**
 * 作品的图片url
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/10 16:12
 **/
@Data
public class ArtworkUrls {
    String mini;
    String original;
    String regular;
    String small;
    String thumb;

    public String getZipUrl() {
        return original == null ? null : original
                .replace("img-original", "img-zip-ugoira")
                .replace("_ugoira0.jpg", "_ugoira1920x1080.zip")
                .replace("_ugoira0.png", "_ugoira1920x1080.zip")
                ;
    }
}
