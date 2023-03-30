package com.gin.pixiv.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.pixiv.api.RankingApi;
import com.gin.pixiv.exception.PixivClientException;
import com.gin.pixiv.exception.PixivException;
import com.gin.pixiv.exception.PixivServerException;
import com.gin.pixiv.response.PixivResponse;
import com.gin.utils.JsonUtils;
import lombok.Setter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

/**
 * 抽象回调
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/27 17:44
 */
@Setter
public abstract class AbstractCallback<T> implements Callback {
    Class<T> eClass;

    public static ResponseBody body(@NotNull Call call, @NotNull Response response) throws IOException, PixivException {
        final int code = response.code();
        final int co = code / 100;
        final ResponseBody body = response.body();
        switch (co) {
            case 3:
            case 2:
                return body;
            case 4:
                if (body == null) {
                    throw new PixivClientException(code, "", call);
                }
                throw new PixivClientException(code, getClientExceptionMessage(call, body.string()), call);
            case 5:
                throw new PixivServerException(code, "服务器异常", call);
            default:
                throw new PixivClientException(code, "非预期的code", call);
        }
    }

    @Override
    public final void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        try {
            try (ResponseBody body = body(call, response)) {
                if (body != null) {
                    onSuccess(parse(body.string()));
                }
            }
        } catch (PixivException e) {
            handleException(e);
        }
    }

    /**
     * 生成客户端错误的报错信息
     * @param call call
     * @param body body
     * @return {@link String}
     * @since 2023/3/29 10:58
     */
    private static String getClientExceptionMessage(Call call, String body) {
        try {
            if (call.request().url().toString().contains(RankingApi.RANKING_PATH)) {
                // 是排行报错
                return JsonUtils.MAPPER.readValue(body, new TypeReference<HashMap<String, String>>() {
                }).get("error");
            }
            return JsonUtils.MAPPER.readValue(body, new TypeReference<PixivResponse<Void>>() {
            }).getMessage();

        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 将字符串解析为指定类型对象
     * @param string body
     * @return 对象
     * @throws JsonProcessingException 解析错误
     */
    public abstract T parse(String string) throws JsonProcessingException;

    /**
     * 执行成功回调
     * @param body body字符串
     */
    public abstract void onSuccess(T body);

    /**
     * 处理异常
     * @param e 异常
     */
    public void handleException(PixivException e) {
        e.printStackTrace();
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        e.printStackTrace();
    }
}   
