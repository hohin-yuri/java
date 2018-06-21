package by.bsuir.socialnetw.service;

import by.bsuir.socialnetw.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public final class AvatarService {

    private static final Logger log = LoggerFactory.getLogger(AvatarService.class);
    private static final ClassLoader loader = AvatarService.class.getClassLoader();

    private AvatarService() {
        try {
            final File folder = new File(loader.getResource(Constants.AVATAR_FOLDER).getFile());
            if (folder.isDirectory()) {
                log.info("");
            } else {
                log.error("");
            }
        } catch (NullPointerException ex) {
            log.error("Avatar folder is not found: {}", ex);
        }
    }

    public static String getPageAvatar(Long id) {
        final String path = Constants.AVATAR_FOLDER + String.valueOf(id) + ".jpg";

        if (null != loader.getResource(path)) {
            return Constants.API_URL + "/" + path;
        }
        return Constants.API_URL + "/" + Constants.AVATAR_FOLDER + "undefined.gif";
    }

    public static String getAvatar(Long id, String fullName) {
        final String path = Constants.AVATAR_FOLDER + String.valueOf(id) + ".jpg";
        if (null != loader.getResource(path)) {
            return Constants.API_URL + "/" + path;
        }
        return fullName;
    }

}
