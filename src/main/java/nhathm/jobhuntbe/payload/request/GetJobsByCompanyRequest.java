package nhathm.jobhuntbe.payload.request;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class GetJobsByCompanyRequest {
    private String companyId;
    private String text;
}
