package nhathm.jobhuntbe.payload.request;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ChangePasswordRequest {
    private String email;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
