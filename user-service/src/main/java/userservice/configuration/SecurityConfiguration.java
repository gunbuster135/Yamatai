package userservice.configuration;

import userservice.security.jwt.JWTAuthenticationFilter;
import userservice.service.JWTLoginService;
import userservice.service.JwtTokenAuthenticationService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Created by Omar on 2016-12-13.
 */
@Configuration
public class SecurityConfiguration {
    @Value("${jwt.secret}")
    private String JWT_SECRET_KEY;

    @Bean
    public StrongPasswordEncryptor strongPasswordEncryptor(){
        return new StrongPasswordEncryptor();
    }

    @Bean
    public JwtTokenAuthenticationService tokenAuthenticationService(){
        return new JwtTokenAuthenticationService(JWT_SECRET_KEY);
    }

    @Bean
    public JWTLoginService jwtLoginService(AuthenticationManager manager,JwtTokenAuthenticationService tokenService){
        return new JWTLoginService(manager,tokenService);
    }
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(JwtTokenAuthenticationService jwtTokenAuthenticationService){
        return new JWTAuthenticationFilter(jwtTokenAuthenticationService);
    }
}
