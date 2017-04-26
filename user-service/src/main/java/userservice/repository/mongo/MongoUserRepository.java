package userservice.repository.mongo;

import userservice.persistence.User;


public interface MongoUserRepository {

    MongoResult<User> findUserByUserName(String username);
    MongoResult<User> findUserByEmail(String email);
    MongoResult<User> findUserByEmailOrUsername(String username, String email);
    MongoResult<User> createUser(User user);
}
