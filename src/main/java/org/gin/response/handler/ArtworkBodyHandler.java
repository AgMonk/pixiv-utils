package org.gin.response.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.ResponseBody;
import org.gin.response.PixivResponse;
import org.gin.response.body.illustmanga.IllustMangaRes;

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
     */
    @Override
    default PixivResponse<IllustMangaRes> convert(ResponseBody body) throws IOException {
        return JSONObject.parseObject(body.string(), new TypeReference<PixivResponse<IllustMangaRes>>() {
        });
    }
}
