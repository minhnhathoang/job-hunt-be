package nhathm.jobhuntbe.service;

import nhathm.jobhuntbe.domain.model.Job;
import nhathm.jobhuntbe.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nhathm
 */
@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public List<Job> getJobsByCompany(String companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

    public Job findById(String id) {
        return jobRepository.findById(id).orElse(null);
    }

    public List<Job> getAll() {
        return jobRepository.findAll();
    }
    public Job save(Job job) {
        return jobRepository.save(job);
    }
}
