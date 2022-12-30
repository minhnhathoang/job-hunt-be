package nhathm.jobhuntbe.repository;

import nhathm.jobhuntbe.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author nhathm
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByToken(String token);
    Optional<User> findById(String id);

    User findByEmailAndPassword(String email, String password);
}
