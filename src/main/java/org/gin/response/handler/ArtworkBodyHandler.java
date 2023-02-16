package org.gin.response.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.ResponseBody;
import org.gin.response.PixivResponse;
import org.gin.response.body.illustmanga.IllustMangaRes;
import org.gin.utils.JsonUtils;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 12:22
 **/
public interface ArtworkBodyHandler extends BaseResponseBodyHandler<PixivResponse<IllustMangaRes>> {
    /**
     * 转换
     * @param body body
     * @return PixivResponse<ArtworkBody>
     * @throws IOException 异常
     */
    @Override
    default PixivResponse<IllustMangaRes> convert(ResponseBody body) throws IOException {
        return JsonUtils.MAPPER.readValue(body.string(), new TypeReference<PixivResponse<IllustMangaRes>>() {
        });
    }
}
