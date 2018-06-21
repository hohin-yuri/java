package by.bsuir.socialnetw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class Constants {

    private static final Logger log = LoggerFactory.getLogger(Constants.class);

    public static final String URI_API_PREFIX = "/api";
    public static final String URI_MESSAGES = URI_API_PREFIX + "/messages";
    public static final String URI_SOCIAL = URI_API_PREFIX + "/social";

    public static final String ERROR_UPDATE_PROFILE = "Error";
    public static final String ERROR_UPDATE_EMAIL = "Used";
    public static final String ERROR_SIGN_UP_EMAIL = ERROR_UPDATE_EMAIL;
    public static final String ERROR_PASSWORD_CONFIRMATION = "Current password is invalid";

    public static String API_URL;
    public static String WEB_URL;
    public static String AVATAR_FOLDER;

    static String REMEMBER_ME_TOKEN;
    static String REMEMBER_ME_COOKIE;

    static final String XSRF_TOKEN_COOKIE_NAME = "XSRF";
    static final String XSRF_TOKEN_HEADER_NAME = "XSRF-TOKEN";

    @Value("${resources.api-url}")
    public void setApiUrl(String path) {
        if (path != null) {
            log.debug("";
        } else {
            log.warn("");
        }

        API_URL = path;
    }

    @Value("${resources.web-url}")
    public void setWebUrl(String path) {
        if (path != null) {
            log.debug("");
        } else {
            log.warn("");
        }

        WEB_URL = path;
    }

    @Value("${resources.avatar-folder}")
    public void setImageFolder(String path) {
        AVATAR_FOLDER = path;
    }

    @Value("${remember-me.token}")
    public void setRememberMeToken(String token) {
        REMEMBER_ME_TOKEN = token;
    }

    @Value("${remember-me.cookie}")
    public void setRememberMeCookie(String cookie) {
        REMEMBER_ME_COOKIE = cookie;
    }

}
