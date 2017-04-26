package userservice.service;


import userservice.model.request.LoginRequestBody;
import userservice.security.jwt.AuthenticationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.io.IOException;

@Service
public class JWTLoginService {
    private final JwtTokenAuthenticationService JwtTokenAuthenticationService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public JWTLoginService(AuthenticationManager authenticationManager, JwtTokenAuthenticationService JwtTokenAuthenticationService) {

        this.authenticationManager = authenticationManager;
        this.JwtTokenAuthenticationService = JwtTokenAuthenticationService;
    }
    public AuthenticationResult attemptAuthentication(LoginRequestBody loginRequestBody)
            throws AuthenticationException, IOException, ServletException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequestBody.getUsername(), loginRequestBody.getPassword());
        try {
            authenticationManager.authenticate(token);
            return new AuthenticationResult(true, JwtTokenAuthenticationService.generateJwt(loginRequestBody.getUsername()));
        } catch (AuthenticationException authException){
            return new AuthenticationResult(false, null);
        }
    }

}