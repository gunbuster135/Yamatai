package userservice.repository.mongo;

import userservice.persistence.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MongoUserRepositoryImpl implements MongoUserRepository {
    @Autowired
    Datastore datastore;

    @Override
    public MongoResult<User> findUserByUserName(String username) {
        final Query<User> query = datastore.createQuery(User.class).field("username").equal(username);
        final User retrievedUser = query.get();
        return new MongoResult<>("Found user",true, retrievedUser);
    }

    @Override
    public MongoResult<User> findUserByEmail(String email) {
        final Query<User> query = datastore.createQuery(User.class).field("email").equal(email);
        final User retrievedUser = query.get();
        return new MongoResult<>("Found user",true, retrievedUser);
    }

    @Override
    public MongoResult<User> findUserByEmailOrUsername(String username, String email) {
        final Query<User> query = datastore.createQuery(User.class);
        query.or(query.criteria("username").equal(username));
        query.or(query.criteria("email").equal(email));
        final User retrievedUser = query.get();
        return new MongoResult<>("Found user",true, retrievedUser);
    }


    public MongoResult<User> findUserById(String id){
        final Query<User> query = datastore.createQuery(User.class).field("_id").equal(id);
        final User retrievedUser = query.get();
        return new MongoResult<>("Found user", true, retrievedUser);
    }

    public boolean deleteUserById(String id){
        final Query<User> query = datastore.createQuery(User.class).field("_id").equal(id);
        return datastore.delete(query).wasAcknowledged();
    }

    @Override
    public MongoResult<User> createUser(User user) {
        datastore.save(user);
        return new MongoResult<>("Created user", true, user);
    }

}
