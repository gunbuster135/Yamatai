package userservice.security.jwt;

/**
 * Created by Omar on 2016-12-14.
 */
public class AuthenticationResult {
    private boolean authenticated;
    private String jwtToken;

    public AuthenticationResult(boolean authenticated, String jwtToken) {
        this.authenticated = authenticated;
        this.jwtToken = jwtToken;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getJwtToken() {
        return jwtToken;
    }

}
