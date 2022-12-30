package nhathm.jobhuntbe.repository;

import nhathm.jobhuntbe.domain.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nhathm
 */
@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

}
