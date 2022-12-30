package nhathm.jobhuntbe.service;

import nhathm.jobhuntbe.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author nhathm
 */
@Service
public class TokenService {
    @Autowired
    UserService userService;
    private HashMap<String, User> tokens = new HashMap<>();

    public User getUserByToken(String token) {
        return userService.findByToken(token);
    }
}
