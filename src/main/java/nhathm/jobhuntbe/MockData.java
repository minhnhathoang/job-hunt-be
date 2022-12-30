package nhathm.jobhuntbe;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import nhathm.jobhuntbe.domain.model.Company;
import nhathm.jobhuntbe.domain.model.User;
import nhathm.jobhuntbe.domain.model.UserDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nhathm
 */
@Component
@Data
public class MockData {
    public List<Company> companies = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    public List<UserDetail> userDetails = new ArrayList<>();
}
