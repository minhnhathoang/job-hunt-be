package nhathm.jobhuntbe.domain.common;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author nhathm
 */
@Data
public class JobFilter {
    private String title;
    private long minSalary;
    private long maxSalary;
    private String location;
    private List<String> companySizes;
    private List<String> jobTypes;
}
