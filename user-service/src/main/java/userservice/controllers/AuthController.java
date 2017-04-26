package userservice.controllers;

import userservice.model.request.LoginRequestBody;
import userservice.model.response.GenericResponseBody;
import userservice.model.response.MetaResponse;
import userservice.security.jwt.AuthenticationResult;
import userservice.service.JWTLoginService;
import userservice.service.JwtTokenAuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

import static userservice.Util.Constants.AUTHORIZATION_HEADER;

/**
 * Created by Omar on 2016-12-12.
 */

@RestController
public class AuthController {

    private Logger log = Logger.getLogger(AuthController.class);
    private final JWTLoginService jwtLoginService;
    private final JwtTokenAuthenticationService tokenAuthenticationService;


    @RequestMapping(value = "/refresh", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    ResponseEntity<GenericResponseBody> refresh(@RequestHeader(AUTHORIZATION_HEADER) String token) {
        GenericResponseBody genericResponseBody = new GenericResponseBody();
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            String newToken = tokenAuthenticationService.refreshJwtToken(token);
            if (newToken != null) {
                headers.add(AUTHORIZATION_HEADER, newToken);
                genericResponseBody.setMeta(new MetaResponse().withCode(HttpStatus.OK.toString())
                        .withMessage("Succesfully refreshed token"));
                return new ResponseEntity<>(genericResponseBody, headers, HttpStatus.OK);
            }
        }
        genericResponseBody.setMeta(new MetaResponse().withCode(HttpStatus.BAD_REQUEST.toString())
                .withMessage("Not a valid token"));
        return new ResponseEntity<>(genericResponseBody, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    ResponseEntity<GenericResponseBody> login(@Validated @RequestBody LoginRequestBody requestBody) throws IOException, ServletException {
        AuthenticationResult authResult = jwtLoginService.attemptAuthentication(requestBody);
        GenericResponseBody responseBody = new GenericResponseBody();
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus status;

        if (authResult.isAuthenticated()) {
            log.info("Succesfully logged in user: " + requestBody.getUsername());
            responseBody.setMeta(new MetaResponse().withCode(HttpStatus.OK.toString())
                    .withMessage("You succesfully logged in"));
            responseHeaders.add(AUTHORIZATION_HEADER, authResult.getJwtToken());
            status = HttpStatus.OK;
        } else {
            responseBody.setMeta(new MetaResponse().withCode(HttpStatus.BAD_REQUEST.toString())
                    .withMessage("You failed to log in"));
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseBody, responseHeaders, status);
    }

    @Autowired
    public AuthController(JWTLoginService jwtLoginService, JwtTokenAuthenticationService tokenAuthenticationService) {
        this.jwtLoginService = jwtLoginService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }
}
