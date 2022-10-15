package org.gin.response.convertor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.ResponseBody;
import org.gin.response.PixivResponse;
import org.gin.response.body.ProfileBody;
import org.gin.response.body.ProfileRealBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 转换器
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 16:00
 **/
public interface Convertor<R> {
    /**
     * 预设的转换器
     * @param responseBody body
     * @return PixivResponse<ProfileRealBody>
     * @throws IOException 异常
     * @since 2022/10/15 10:14
     */
    static PixivResponse<ProfileRealBody> profileAll(ResponseBody responseBody) throws IOException {
        final PixivResponse<ProfileBody> response = JSONObject.parseObject(responseBody.string(), new TypeReference<>() {
        });
        final ProfileRealBody body = new ProfileRealBody(response.getBody());
        final PixivResponse<ProfileRealBody> res = new PixivResponse<>();
        res.setBody(body);
        return res;
    }

    /**
     * 通用转换器
     * @param responseBody ResponseBody
     * @param clazz        body类型
     * @return org.gin.response.PixivResponse<T>
     * @throws IOException 异常
     * @since 2022/10/15 11:01
     */
    static <T> PixivResponse<T> common(ResponseBody responseBody, Class<T> clazz) throws IOException {
        String string = responseBody.string();
        string = replaceEmptyArray(string, "tagTranslation");
        final PixivResponse<String> response = JSONObject.parseObject(string, new TypeReference<>() {
        });
        final T t = JSONObject.parseObject(response.getBody(), clazz);
        final PixivResponse<T> res = new PixivResponse<>();
        res.setBody(t);
        return res;
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
