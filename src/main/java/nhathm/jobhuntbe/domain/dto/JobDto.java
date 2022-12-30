package nhathm.jobhuntbe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import nhathm.jobhuntbe.domain.model.Company;
import nhathm.jobhuntbe.domain.model.Job;
import nhathm.jobhuntbe.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author nhathm
 */
@Data
@AllArgsConstructor
public class JobDto {
    private Job job;
    private Company company;
    private User hiringContact;
    private boolean applied;
    private boolean saved;
}
