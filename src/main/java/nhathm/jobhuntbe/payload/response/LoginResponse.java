package nhathm.jobhuntbe.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import nhathm.jobhuntbe.domain.model.User;
import nhathm.jobhuntbe.payload.request.BaseResponse;

/**
 * @author nhathm on 2022-12-25
 */
@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private User user;
}
