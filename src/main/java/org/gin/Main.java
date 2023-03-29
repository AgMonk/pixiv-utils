package org.gin;

import org.gin.exception.PixivException;
import org.gin.pixiv.api.CommentIllustApi;
import org.gin.pixiv.main.PixivClient;

import java.io.IOException;

/**
 * @author bx002
 * @since 2022/11/18 15:45
 */
public class Main {
    public static final String sessionId = "57680761_Rsxxd8c07cZeLxwemUbKQVWL4aUe5O8T";
    public static final String token = "5766b55c3cf85d479601894a4ca0d1fb";

    public static void main(String[] args) throws PixivException, IOException {

        final PixivClient client = new PixivClient(sessionId, token);

//        final IllustApi illustApi = new IllustApi(client);
//        illustApi.zTest();
        new CommentIllustApi(client).zTest();

    }
}