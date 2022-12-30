package nhathm.jobhuntbe.controller;

import nhathm.jobhuntbe.aop.ApiException;
import nhathm.jobhuntbe.domain.model.User;
import nhathm.jobhuntbe.payload.request.*;
import nhathm.jobhuntbe.payload.response.LoginResponse;
import nhathm.jobhuntbe.service.TokenService;
import nhathm.jobhuntbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author nhathm
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/sign-up")
    public BaseResponse signUp(@RequestBody SignupRequest request) throws ApiException {
        User user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user == null) {
            user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setFullName(request.getFullName());
            user.setAvatarUrl("https://cdn-icons-png.flaticon.com/512/6386/6386976.png");
            userService.save(user);

            return BaseResponse.success();
        }
        throw new ApiException(400, "Invalid request");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) throws ApiException {
        User user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user != null) {
            String token = LocalDate.now() + user.getId();
            user.setToken(token);
            userService.save(user);

            return new LoginResponse(token, user);
        } else {
            throw new ApiException(520, "Login failed");
        }
    }

    @PostMapping("/save-fcm-token")
    public BaseResponse saveFcmToken(@RequestBody SaveFcmTokenRequest request) throws ApiException {
        try {
            User user = userService.findById(request.getUserId());
            user.setFcmToken(request.getToken());
            userService.save(user);
            return BaseResponse.success();
        } catch (Exception ex) {
            throw new ApiException(520, "Save fcm token failed");
        }
    }

    @PostMapping("/change-password")
    public BaseResponse changePassword(@RequestBody ChangePasswordRequest request) throws ApiException {
        if (request.getNewPassword().equals(request.getConfirmPassword())) {
            User user = userService.findByEmailAndPassword(request.getEmail(), request.getConfirmPassword());
            user.setPassword(request.getNewPassword());
            userService.save(user);
            return BaseResponse.success();
        } else {
            throw new ApiException(520, "Change password failed");
        }
    }
}
