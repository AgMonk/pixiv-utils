package org.gin.api.groups;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import org.gin.api.PixivApi;
import org.gin.emuns.PixivStamp;
import org.gin.exception.PixivRequestException;
import org.gin.params.comment.PostCommentParam;
import org.gin.request.PixivRequest;
import org.gin.request.PixivUrl;
import org.gin.response.SimplePixivResponse;
import org.gin.response.body.comment.PostCommentRes;
import org.gin.response.convertor.Convertor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

import static org.gin.request.PixivRequestBody.createFormBody;

/**
 * 绘画评论API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/11/17 13:23
 */
@Getter
@Setter
@RequiredArgsConstructor
public class CommentIllustApi {
    private final PixivApi api;

    /**
     * 删除评论
     * @param id        作品id
     * @param commentId 评论id
     * @return org.gin.request.PixivRequest<org.gin.response.SimplePixivResponse>
     * @since 2022/11/17 13:47
     */
    public PixivRequest<SimplePixivResponse> del(long id, long commentId) {
        final HashMap<String, Long> body = new HashMap<>(2);
        body.put("i_id", id);
        body.put("del_id", commentId);

        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/rpc_delete_comment.php")
                .build();
        return new PixivRequest<>(url, api.getClient(), createFormBody(body));

    }

    /**
     * 发表评论
     * @param param 参数
     * @return org.gin.request.PixivRequest<PostCommentRes>
     * @since 2022/11/17 13:46
     */
    public PixivRequest<PostCommentRes> post(@NotNull PostCommentParam.Illust param) {
        final HttpUrl url = new PixivUrl.Builder()
                .setUrl(api.getDomain() + "/rpc/post_comment.php")
                .build();
        return new PixivRequest<>(url, api.getClient(), createFormBody(param));
    }

    public void zTest() throws PixivRequestException, IOException {
        long pid = 99147997;
        long uid = 20670838;

        zTestPostDelete(pid, uid);
    }

    private void zTestPostDelete(long pid, long uid) throws PixivRequestException, IOException {
        final PostCommentParam.Illust param = new PostCommentParam.Illust(pid, uid, null, PixivStamp.Stamp_304);
        final PostCommentRes res = post(param).sync(body -> Convertor.common2(body, PostCommentRes.class));
        final Long commentId = res.getBody().getCommentId();
        System.out.println("commentId = " + commentId);
        del(pid, commentId).sync(Convertor::common);
    }

}
