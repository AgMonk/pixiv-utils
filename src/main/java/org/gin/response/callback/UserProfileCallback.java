package org.gin.response.callback;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import okhttp3.ResponseBody;
import org.gin.response.PixivResponse;
import org.gin.response.body.user.ProfileBody;
import org.gin.response.body.user.ProfileRealBody;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 16:55
 **/
public interface UserProfileCallback extends BasePixivCallback   {
    /**
     * 请求成功时的处理方法
     * @param responseBody 响应body
     * @throws IOException 异常
     */
    @Override
    default void onSuccess(ResponseBody responseBody) throws IOException {
        PixivResponse<ProfileBody> response = JSONObject.parseObject(responseBody.string(),new TypeReference<>(){});
        handleBody(new ProfileRealBody(response.getBody()));
    }

    /**
     * 处理body数据
     * @param body  body数据
     * @throws IOException 异常
     */
    void handleBody(ProfileRealBody body) throws IOException;
}
