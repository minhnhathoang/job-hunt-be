package nhathm.jobhuntbe.domain.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author nhathm on 2022-12-23
 */
@Data
@Document("Job")
public class Job {
    @Id
    private String id;
    private String title;
    private String description;
    private String jobType;
    private String companyId;
    private String hiringContactId;
    private String location;
    private int minSalary;
    private int maxSalary;
    private String workType;
    private String yearsOfExperience;
    private List<String> skills;

    @LastModifiedDate
    private Date updateAt;
    @CreatedDate
    private Date createdAt;
}
