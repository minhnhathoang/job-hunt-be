package nhathm.jobhuntbe.repository;

import nhathm.jobhuntbe.domain.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nhathm
 */
@Repository
public interface JobRepository extends MongoRepository<Job, String> {

    List<Job> findByCompanyId(String companyId);
}
