package org.gin.pixiv.api;

import lombok.RequiredArgsConstructor;
import org.gin.pixiv.call.PixivCallStandard;
import org.gin.pixiv.callback.StandardCallback;
import org.gin.pixiv.enums.ParamType;
import org.gin.pixiv.enums.PixivStamp;
import org.gin.pixiv.main.PixivClient;
import org.gin.pixiv.params.comment.CommentDelParam;
import org.gin.pixiv.params.comment.CommentRepliesParam;
import org.gin.pixiv.params.comment.IllustsCommentRootsParam;
import org.gin.pixiv.params.comment.PostCommentParam;
import org.gin.pixiv.response.body.comment.CommentReply;
import org.gin.pixiv.response.body.comment.CommentsBody;
import org.gin.pixiv.response.body.comment.PostCommentBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * 绘画评论API
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/3/29 15:24
 */
@RequiredArgsConstructor
public class CommentIllustApi {
    private final PixivClient client;

    /**
     * 查询作品评论楼中楼
     * @param param 参数
     * @return {@link PixivCallStandard<CommentsBody>}
     * @since 2023/3/29 15:43
     */
    public PixivCallStandard<CommentsBody> getReplies(@NotNull CommentRepliesParam param) {
        return client.standard("/ajax/illusts/comments/replies", CommentsBody.class, param);
    }

    /**
     * 查询作品评论(根)
     * @param param 参数
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.comment.CommentsBody>
     * @since 2023/3/29 15:43
     */
    public PixivCallStandard<CommentsBody> getRoots(@NotNull IllustsCommentRootsParam param) {
        return client.standard("/ajax/illusts/comments/roots", CommentsBody.class, param);

    }

    /**
     * 删除评论
     * @param param 参数
     * @return org.gin.pixiv.call.PixivCallStandard<java.lang.String>
     * @since 2023/3/29 15:44
     */
    public PixivCallStandard<String> postDel(CommentDelParam param) {
        return client.standard(ParamType.FORM, "/rpc_delete_comment.php", String.class, param);
    }

    /**
     * 发表评论
     * @param param 参数
     * @return org.gin.pixiv.call.PixivCallStandard<org.gin.pixiv.response.body.comment.PostCommentBody>
     * @since 2023/3/29 15:44
     */
    public PixivCallStandard<PostCommentBody> postReply(@NotNull PostCommentParam.Illust param) {
        return client.standard(ParamType.FORM, "/rpc/post_comment.php", PostCommentBody.class, param);
    }

    public void zTest() throws IOException {
        long pid = 99147997;
        long uid = 20670838;

        zTestPostDelete(pid, uid);
        zTestRootReply(pid);
    }

    private void zTestPostDelete(long pid, long uid) {
        final PostCommentParam.Illust param = new PostCommentParam.Illust(pid, uid, null, PixivStamp.Stamp_304);
        postReply(param).async(new StandardCallback<PostCommentBody>() {
            @Override
            public void onSuccess(PostCommentBody body) {
                final Long commentId = body.getCommentId();
                System.out.println("commentId = " + commentId);
                try {
                    postDel(new CommentDelParam(pid, commentId)).sync();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void zTestRootReply(long pid) {
        final IllustsCommentRootsParam param = new IllustsCommentRootsParam(pid, 1, 20);
        getRoots(param).async(new StandardCallback<CommentsBody>() {
            @Override
            public void onSuccess(CommentsBody body) {
                final CommentReply reply = body.getComments().stream().filter(CommentReply::getHasReplies).collect(Collectors.toList()).get(0);
                if (reply != null) {
                    System.out.println(reply.getId());
                    try {
                        final CommentsBody replies = getReplies(new CommentRepliesParam(reply.getId(), 1)).sync();
                        final CommentReply commentReply = replies.getComments().get(0);
                        System.out.println(commentReply.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


}   
