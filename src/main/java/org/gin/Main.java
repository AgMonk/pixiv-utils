package org.gin;

import org.gin.exception.PixivException;
import org.gin.pixiv.call.PixivCallStandard;
import org.gin.pixiv.callback.StandardCallback;
import org.gin.pixiv.main.PixivClient;
import org.gin.pixiv.response.body.illust.IllustBodyDetail;
import org.gin.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;

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

        final PixivCallStandard<IllustBodyDetail> call = client.standard("/ajax/illust/" + 99147997,
                                                                         null,
                                                                         IllustBodyDetail.class);

        call.async(new StandardCallback<IllustBodyDetail>() {
            @Override
            public void onSuccess(@NotNull IllustBodyDetail body) {
                JsonUtils.printJson(body);
            }
        });

    }
}