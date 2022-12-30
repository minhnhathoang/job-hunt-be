package nhathm.jobhuntbe.service;

import nhathm.jobhuntbe.domain.model.User;
import nhathm.jobhuntbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nhathm
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByToken(String id) {
        return userRepository.findByToken(id).orElse(null);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
    public User save(User user) {
        return userRepository.save(user);
    }
}
