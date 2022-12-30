package nhathm.jobhuntbe.payload.request;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class CreateJobRequest {
    private String title;
    private String description;
    private String jobType;
    private String location;
    private int  minSalary;
    private int  maxSalary;
    private String workType;
    private String yearsOfExperience;
    private String skills;

    private String companyId;
}
