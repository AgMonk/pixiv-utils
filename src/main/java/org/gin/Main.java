package org.gin;

import org.gin.exception.PixivException;
import org.gin.pixiv.main.PixivClient;
import org.gin.response.body.user.ProfileAllBody;

import java.io.IOException;
import java.util.List;

/**
 * @author bx002
 * @since 2022/11/18 15:45
 */
public class Main {
    public static final String SESSION_ID = "57680761_Rsxxd8c07cZeLxwemUbKQVWL4aUe5O8T";
    public static final String TOKEN = "5766b55c3cf85d479601894a4ca0d1fb";

    public static void main(String[] args) throws PixivException, IOException {

        final PixivClient client = new PixivClient(SESSION_ID, TOKEN);

//        new UserBookmarkApi(client).zTest();

        final ProfileAllBody body = client.standard("/ajax/user/33397446/profile/all", ProfileAllBody.class).sync();
        final List<Object> pickup = body.getPickup();
        pickup.forEach(item -> System.out.println(item.getClass()));
    }
}