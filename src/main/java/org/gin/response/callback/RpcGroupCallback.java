package org.gin.response.callback;

import com.alibaba.fastjson.JSONObject;
import okhttp3.ResponseBody;
import org.gin.response.RpcGroupResponse;

import java.io.IOException;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/11 16:55
 **/
public interface RpcGroupCallback extends BasePixivCallback  {
    /**
     * 请求成功时的处理方法
     * @param responseBody 响应body
     * @throws IOException 异常
     */
    @Override
    default void onSuccess(ResponseBody responseBody) throws IOException {
        handleBody(JSONObject.parseObject(responseBody.string(),RpcGroupResponse.class));
    }

    /**
     * 处理body数据
     * @param body  body数据
     */
    void handleBody(RpcGroupResponse body);
}
