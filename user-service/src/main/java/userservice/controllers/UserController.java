package userservice.controllers;

import userservice.model.request.RegisterRequestBody;
import userservice.model.response.GenericResponseBody;
import userservice.model.response.MetaResponse;
import userservice.persistence.User;
import userservice.repository.mongo.MongoResult;
import userservice.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);

    final UserService userService;


    @RequestMapping("/")
    public String index(){
        return "test";
    }

    @RequestMapping("/users") /* Maps to all HTTP actions by default (GET,POST,..)*/
    public @ResponseBody String getUsers() {
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GenericResponseBody> register(@Validated @RequestBody RegisterRequestBody registerRequestBody){
        GenericResponseBody response = new GenericResponseBody();
        MongoResult<User> result = userService.createUser(registerRequestBody);
        MetaResponse metaResponse = new MetaResponse();
        metaResponse.setMessage(result.getMessage());
        HttpStatus httpStatus;
        if(result.isSuccess()){
            metaResponse.setCode(HttpStatus.OK.toString());
            httpStatus = HttpStatus.OK;
        }else {
            metaResponse.setCode(HttpStatus.BAD_REQUEST.toString());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        response.setMeta(metaResponse);
        return new ResponseEntity<>(response,httpStatus);
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

}
