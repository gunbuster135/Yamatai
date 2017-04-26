package userservice.security;


import userservice.security.jwt.JWTAuthenticationFilter;
import userservice.security.authentication.MongoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MongoAuthenticationProvider mongoAuthenticationProvider;

    private final JWTAuthenticationFilter authenticationFilter;

    @Autowired
    public WebSecurityConfig(JWTAuthenticationFilter authenticationFilter, MongoAuthenticationProvider mongoAuthenticationProvider) {
        this.authenticationFilter = authenticationFilter;
        this.mongoAuthenticationProvider = mongoAuthenticationProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http.headers().cacheControl();
        http.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Expose-Headers",
                "accept, authorization, content-type, x-requested-with, jwt"));

        http.csrf().disable() // disable csrf for our requests.
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login","/refresh","/register").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(mongoAuthenticationProvider);

    }

}
