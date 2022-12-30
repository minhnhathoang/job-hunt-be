package nhathm.jobhuntbe.payload;

import nhathm.jobhuntbe.domain.model.Company;
import nhathm.jobhuntbe.domain.model.Salary;

/**
 * @author nhathm on 2022-12-25
 */
public class JobDetail {
    private String id;
    private String title;
    private String description;
    private String location;
    private String type;
    private Salary salary;
    private Company company;
    private String companyLogo;
    private String companyWebsite;
    private String companyDescription;
    private String applyUrl;
    private String applyEmail;
    private String applyPhone;
    private String applyAddress;
    private String applyDescription;
    private String createdAt;
    private String updatedAt;
}
