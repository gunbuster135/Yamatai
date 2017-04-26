package userservice.Util;

/**
 * Created by Omar on 2016-12-14.
 */
public class Constants {
    /* JWT CONSTANTS */
    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String AUTHORIZATION_PREFIX = "Bearer";
    public final static long   TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10;
    private Constants(){

    }
}
