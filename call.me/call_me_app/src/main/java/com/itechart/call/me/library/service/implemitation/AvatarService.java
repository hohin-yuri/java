package com.itechart.call.me.library.service.implemitation;

import com.itechart.call.me.library.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/** This class represents
 * service that works
 * with avatars
 *
 * @author Hohin Yury
 *
 */
public final class AvatarService {

    private static final Resource resource = Resource.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    private static final ClassLoader loader = AvatarService.class.getClassLoader();
    private static final String AVATAR_FOLDER = resource.getProperty("AVATAR_FOLDER");
    private static final String API_URL = resource.getProperty("API_URL");

    private AvatarService() {
        if (!new File(loader.getResource(AVATAR_FOLDER).getFile()).isDirectory()) {
            logger.error("Avatar folder {} is not found", AVATAR_FOLDER);
        }
    }

    /**
     * This method gets avatar of contact
     * if can't find avatar returns
     * default avatar
     * @param id - id of corresponding contact entity
     */
    public static String getAvatar(String staticFolderPath, Long id) {
        final String path = staticFolderPath + AVATAR_FOLDER + String.valueOf(id) + ".jpg";
        InputStream is = null;
        try {
            is = new FileInputStream(new File(path));
            return API_URL + id + ".jpg";
        } catch (FileNotFoundException e) {
            return API_URL + "undefined.gif";
        }
    }

    /**
     * Saves avatar of contact in a file system
     * @param id - id of corresponding contact entity
     * @param avatar - multipart file from controller
     */
    public static String saveAvatar(String staticFolderPath, Long id, MultipartFile avatar) {
        final String path = staticFolderPath + AVATAR_FOLDER + String.valueOf(id) + ".jpg";

        File convFile = new File(path);
        try {
            if(!convFile.exists()) {
                convFile.createNewFile();
            }
            logger.info("Saving avatar in " + path);
            FileOutputStream fos;
            fos = new FileOutputStream(convFile);
            fos.write(avatar.getBytes());
            fos.close();
        } catch (IOException e) {
            logger.error("Can't save avatar to folder!" + e);
            return null;
        }
        return API_URL + String.valueOf(id) + ".jpg";
    }


}

