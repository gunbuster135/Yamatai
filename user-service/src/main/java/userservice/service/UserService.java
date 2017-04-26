package userservice.service;

import userservice.persistence.Role;
import userservice.persistence.User;
import userservice.model.request.RegisterRequestBody;
import userservice.repository.mongo.MongoUserRepository;
import userservice.repository.mongo.MongoResult;
import userservice.Util.SecurityUtil;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserService {
    private final MongoUserRepository mongoUserRepository;
    private final StrongPasswordEncryptor passwordEncryptor;

    @Autowired
    public UserService(MongoUserRepository mongoUserRepository, StrongPasswordEncryptor passwordEncryptor) {
        this.mongoUserRepository = mongoUserRepository;
        this.passwordEncryptor = passwordEncryptor;
    }


    public MongoResult<User> createUser(RegisterRequestBody registerBody){
        String salt = SecurityUtil.generateSalt();
        String encryptedPassword = saltAndEncrypt(registerBody.getPassword(),salt);
        User user = new User(registerBody.getUsername(), encryptedPassword, Collections.singletonList(new Role("ROLE_USER")),
                salt,  registerBody.getEmail());
        return mongoUserRepository.createUser(user);
    }

    private String saltAndEncrypt(String password,String salt){
        String saltedPassword = password+""+salt;
        return passwordEncryptor.encryptPassword(saltedPassword);
    }

}
