package org.gin.response.convertor;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.ResponseBody;
import org.gin.response.PixivResponse;
import org.gin.response.SimplePixivResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static org.gin.utils.JsonUtils.MAPPER;

/**
 * 转换器
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 16:00
 **/
public interface Convertor<R> {

    /**
     * 通用转换器
     * @param responseBody ResponseBody
     * @param clazz        body类型
     * @return org.gin.response.PixivResponse<T>
     * @throws IOException 异常
     * @since 2022/10/15 11:01
     */
    static <T> T common(ResponseBody responseBody, Class<T> clazz) throws IOException {
        return MAPPER.readValue(responseBody.string(), clazz);
    }

    static SimplePixivResponse simple(ResponseBody responseBody) throws IOException {
        return MAPPER.readValue(responseBody.string(), SimplePixivResponse.class);
    }

    /**
     * 通用转换器
     * @param responseBody ResponseBody
     * @return org.gin.response.PixivResponse<T>
     * @throws IOException 异常
     * @since 2022/10/15 11:01
     */
    static PixivResponse<Void> toVoid(ResponseBody responseBody) throws IOException {
        return MAPPER.readValue(responseBody.string(), new TypeReference<PixivResponse<Void>>() {
        });
    }

    /**
     * 把来源json字符串中指定字段名的空数组，修改为空对象
     * @param source json字符串
     * @param field  字段名
     * @return 字符串
     */
    static String replaceEmptyArray(String source, @NotNull String field) {
        final String r1 = String.format("\"%s\":[]", field);
        final String r2 = String.format("\"%s\":{}", field);
        return source.replace(r1, r2);
    }


    /**
     * 从 ResponseBody 转换到R的方法
     * @param responseBody ResponseBody
     * @return R
     * @throws IOException 异常
     */
    R convert(ResponseBody responseBody) throws IOException;
}
