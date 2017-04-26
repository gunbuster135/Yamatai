package userservice.repository.mongo;

import userservice.persistence.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Omar on 2017-03-22.
 */
public interface UserRepository extends CrudRepository<User,Long> {



}
