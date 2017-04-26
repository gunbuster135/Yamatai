package userservice.service;


import userservice.security.jwt.AuthenticatedUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static userservice.Util.Constants.*;

public class JwtTokenAuthenticationService {

    private String secret;

    public JwtTokenAuthenticationService(String secret){
        this.secret = secret;
    }

    public String generateJwt(String username) {
        // We generate a token now.
       String jwtToken = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
       return AUTHORIZATION_PREFIX+" "+jwtToken;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (token != null) {
            token = token.replaceFirst("^Bearer","");
            // parse the token.
            String username = parseJwt(token);
            if (username != null) // we managed to retrieve a user
            {
                return new AuthenticatedUser(username);
            }
        }
        return null;
    }
    public String refreshJwtToken(String token){
        String username = parseJwt(token);
        if(username != null){
            return generateJwt(token);
        }
        return null;
    }

    private String parseJwt(String token){
        return  Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

