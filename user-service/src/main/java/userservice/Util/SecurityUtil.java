package userservice.Util;

import static java.util.UUID.randomUUID;

/**
 * Created by Omar on 2016-12-13.
 */
public class SecurityUtil {

    public static String generateUUID(){
        return randomUUID().toString();
    }

    public static String generateSalt(){
        String uuid = generateUUID();
        return uuid.substring(0,5);
    }


}
