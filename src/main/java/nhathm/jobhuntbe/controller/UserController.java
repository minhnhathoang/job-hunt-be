package nhathm.jobhuntbe.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import nhathm.jobhuntbe.domain.model.User;
import nhathm.jobhuntbe.service.FCMService;
import nhathm.jobhuntbe.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nhathm on 2022-12-28
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    TokenService tokenService;

    @Autowired
    FCMService fcmService;

    @GetMapping("/list")
    public List<User> users() {
        return null;
    }

    @GetMapping("/by-auth")
    public User getUserByToken(HttpServletRequest request) {
        System.out.println("real token header: " + request.getHeader("access_token"));
        User user = tokenService.getUserByToken(request.getHeader("access_token"));
        System.out.println("USER-AUTH: " + user);
//        fcmService.sendFcmMessage("dasdsa", "fx78kWh3SNiDB9zOPlBE1c:APA91bFkie980i2_-vEXut17826cyvY9iAgtJ_otCM7FS4Ue5Oo8eIpqnkWbW96B-3HHSDIL330uOs1HhFybDjrAHAM1CCkvYhcqYyplhEaBT_htBp-XXz-ZBJ1kPkZ9VGw2PZ3d_C2k");
        return user;
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user, HttpServletRequest request) {

        return null;
    }
}
