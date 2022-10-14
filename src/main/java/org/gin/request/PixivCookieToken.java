package org.gin.request;

import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.gin.exception.PixivException;
import org.gin.exception.PixivExceptionEnum;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pixiv cookie token
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/14 11:25
 **/
@Getter
public class PixivCookieToken {
    private static final Pattern PATTERN = Pattern.compile("pixiv.context.token = \"(.+?)\";");
    private static final String URL = "/setting_user.php";

    final long userId;
    final String cookie;
    String token;

    public PixivCookieToken(String phpSessionId, String token) {
        this.cookie = "PHPSESSID=" + phpSessionId;
        this.userId = Long.parseLong(phpSessionId.split("_")[0]);
        this.token = token;
    }

    public PixivCookieToken(String phpSessionId) {
        this(phpSessionId, null);
    }

    public String findToken(OkHttpClient client) throws PixivException, IOException {
        final HttpUrl httpUrl = PixivCommon.createHttpUrl(PixivUrl.DOMAIN + URL);
        final Request request = PixivCommon.createGetRequest(cookie, httpUrl);
        try (final Response response = client.newCall(request).execute()) {
            final String html = Objects.requireNonNull(response.body()).string();
            final Matcher matcher = PATTERN.matcher(html);
            if (matcher.find()) {
                final String s = matcher.group(1);
                this.token = s;
                return s;
            }
        }
        throw new PixivException(PixivExceptionEnum.TOKEN_NOT_FOUND);
    }

}
