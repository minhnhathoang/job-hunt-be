package nhathm.jobhuntbe.payload.request;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class SaveFcmTokenRequest {
    private String userId;
    private String token;
}
