package nhathm.jobhuntbe.payload.request;

import lombok.Data;

/**
 * @author nhathm on 2022-12-23
 */
@Data
public class LoginRequest {
    private String email;
    private String password;
}
