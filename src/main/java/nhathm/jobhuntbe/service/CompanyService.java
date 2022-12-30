package nhathm.jobhuntbe.service;

import nhathm.jobhuntbe.domain.model.Company;
import nhathm.jobhuntbe.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nhathm
 */
@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company findById(String id) {
        return companyRepository.findById(id).orElse(null);
    }
    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
