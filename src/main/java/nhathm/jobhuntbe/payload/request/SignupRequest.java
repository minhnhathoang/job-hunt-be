package nhathm.jobhuntbe.payload.request;

import lombok.Data;

/**
 * @author nhathm on 2022-12-23
 */
@Data
public class SignupRequest {
    private String fullName;
    private String email;
    private String password;
}
