package nhathm.jobhuntbe.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import nhathm.jobhuntbe.domain.common.JobFilter;
import nhathm.jobhuntbe.domain.dto.JobDto;
import nhathm.jobhuntbe.domain.model.Company;
import nhathm.jobhuntbe.domain.model.Job;
import nhathm.jobhuntbe.domain.model.User;
import nhathm.jobhuntbe.payload.request.CreateJobRequest;
import nhathm.jobhuntbe.payload.request.GetJobsByCompanyRequest;
import nhathm.jobhuntbe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author nhathm
 */
@Log4j2
@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    JobService jobService;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    FCMService fcmService;
    @Autowired
    TokenService tokenService;

    HashMap<String, List<String>> saved = new HashMap<>();
    HashMap<String, List<String>> applied = new HashMap<>();
    HashMap<String, List<String>> activeJobs = new HashMap<>();

    @GetMapping("/jobs-by-company")
    public List<JobDto> getJobsByCompany(HttpServletRequest httpRequest) {
        User user = tokenService.getUserByToken(httpRequest.getHeader("access_token"));
        List<Job> jobs = jobService.getJobsByCompany(tokenService.getUserByToken(httpRequest.getHeader("access_token")).getCompanyId());
        List<JobDto> jobDtos = new ArrayList<>();
        if (applied.get(user.getId()) == null) {
            applied.put(user.getId(), new ArrayList<>());
        }
        if (saved.get(user.getId()) == null) {
            saved.put(user.getId(), new ArrayList<>());
        }
        for (Job job : jobs) {
            jobDtos.add(new JobDto(job, companyService.findById(job.getCompanyId()), userService.findById(job.getHiringContactId()),
                    saved.get(user.getId()).contains(job.getId()), applied.get(user.getId()).contains(job.getId())));
        }
        return jobDtos;
    }

    @GetMapping("/applied-jobs")
    public List<JobDto> getAppliedJobs(HttpServletRequest httpRequest) {
        User user = tokenService.getUserByToken(httpRequest.getHeader("access_token"));

        if (applied.get(user.getId()) == null) {
            applied.put(user.getId(), new ArrayList<>());
        }
        if (saved.get(user.getId()) == null) {
            saved.put(user.getId(), new ArrayList<>());
        }
        List<JobDto> jobDtos = new ArrayList<>();
        for (String jobId : applied.get(user.getId())) {
            Job job = jobService.findById(jobId);
            if (job != null) {
                Company company = companyService.findById(job.getCompanyId());
                jobDtos.add(new JobDto(job, company, userService.findById(job.getHiringContactId()), applied.get(user.getId()).contains(job.getId()), saved.get(user.getId()).contains(job.getId())));
            }
        }
        return jobDtos;
    }

    @GetMapping("/saved-jobs")
    public List<JobDto> getSavedJobs(HttpServletRequest httpRequest) {
        User user = tokenService.getUserByToken(httpRequest.getHeader("access_token"));

        if (applied.get(user.getId()) == null) {
            applied.put(user.getId(), new ArrayList<>());
        }
        if (saved.get(user.getId()) == null) {
            saved.put(user.getId(), new ArrayList<>());
        }
        List<JobDto> jobDtos = new ArrayList<>();
        for (String jobId : saved.get(user.getId())) {
            Job job = jobService.findById(jobId);
            if (job != null) {
                Company company = companyService.findById(job.getCompanyId());
                jobDtos.add(new JobDto(job, company, userService.findById(job.getHiringContactId()), applied.get(user.getId()).contains(job.getId()), saved.get(user.getId()).contains(job.getId())));
            }
        }
        return jobDtos;
    }

    @PostMapping("/filter")
    public List<JobDto> filtJobs(@RequestBody JobFilter filter, HttpServletRequest httpRequest) {
        User user = tokenService.getUserByToken(httpRequest.getHeader("access_token"));

        List<Job> jobs = jobService.getAll();

        List<JobDto> jobDtos = new ArrayList<>();

        for (Job job : jobs) {
            log.info(job);
            if (!job.getTitle().toLowerCase().contains(filter.getTitle().toLowerCase().trim())) {
                log.info("title not match");
                continue;
            }
            System.out.println(filter.getLocation().toLowerCase().trim());
            System.out.println(job.getLocation().toLowerCase().trim());
            if (filter.getLocation() != null && !filter.getLocation().isEmpty() && !job.getLocation().toLowerCase().contains(filter.getLocation().toLowerCase().trim())) {
                log.info("location not match");
                continue;
            }
            if (filter.getMaxSalary() < job.getMinSalary()) {
                log.info("max salary not match");
                continue;
            }
            if (filter.getMinSalary() > job.getMaxSalary()) {
                log.info("min salary not match");
                continue;
            }
            if (filter.getJobTypes().size() > 0 && !filter.getJobTypes().contains(job.getJobType())) {
                log.info("job type not match");
                continue;
            }
            Company company = companyService.findById(job.getCompanyId());
            if (filter.getCompanySizes().size() > 0 && !filter.getCompanySizes().contains(company.getCompanySize())) {
                continue;
            }
            if (applied.get(user.getId()) == null) {
                applied.put(user.getId(), new ArrayList<>());
            }
            if (saved.get(user.getId()) == null) {
                saved.put(user.getId(), new ArrayList<>());
            }
            jobDtos.add(new JobDto(job, company, userService.findById(job.getHiringContactId()), applied.get(user.getId()).contains(job.getId()), saved.get(user.getId()).contains(job.getId())));
        }

        return jobDtos;
    }

    @PostMapping("/apply")
    public JobDto apply(@RequestBody JobDto jobDto, HttpServletRequest httpRequest) {
        User user = tokenService.getUserByToken(httpRequest.getHeader("access_token"));

        if (applied.get(user.getId()) == null) {
            applied.put(user.getId(), new ArrayList<>());
        }
        if (applied.get(user.getId()).contains(jobDto.getJob().getId())) {
            applied.get(user.getId()).remove(jobDto.getJob().getId());
            jobDto.setApplied(false);
            return jobDto;
        } else {
            System.out.println(applied.get(user.getId()).size());
            applied.get(user.getId()).add(jobDto.getJob().getId());
            System.out.println(applied.get(user.getId()).size());
            jobDto.setApplied(true);
            fcmService.sendFcmMessage("Job Apply", user.getFullName() + " have applied for job " + jobDto.getJob().getTitle(),
                    jobDto.getHiringContact().getFcmToken());
            return jobDto;
        }
    }

    @PostMapping("/save")
    public JobDto save(@RequestBody JobDto jobDto, HttpServletRequest httpRequest) {
        User user = tokenService.getUserByToken(httpRequest.getHeader("access_token"));
        if (saved.get(user.getId()) == null) {
            saved.put(user.getId(), new ArrayList<>());
        }
        if (saved.get(user.getId()).contains(jobDto.getJob().getId())) {
            saved.get(user.getId()).remove(jobDto.getJob().getId());
            jobDto.setSaved(false);
            return jobDto;
        } else {
            saved.get(user.getId()).add(jobDto.getJob().getId());
            jobDto.setSaved(true);
            return jobDto;
        }
    }

    @PostMapping("/create")
    public JobDto createJob(@RequestBody CreateJobRequest request, HttpServletRequest httpRequest) {
        User user = tokenService.getUserByToken(httpRequest.getHeader("access_token"));

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setJobType(request.getJobType());
        job.setLocation(request.getLocation());
        job.setMinSalary(request.getMinSalary());
        job.setMaxSalary(request.getMaxSalary());
        job.setYearsOfExperience(request.getYearsOfExperience());
        job.setSkills(Arrays.stream(request.getSkills().split(",")).toList().stream().map(String::trim).toList());
        job.setWorkType(request.getWorkType());

        job.setCompanyId(user.getCompanyId());
        job.setHiringContactId(user.getId());

        jobService.save(job);
        return new JobDto(job, companyService.findById(user.getCompanyId()), user, false, false);
    }

    @GetMapping("/locations")
    public List<String> getLocations() {
        return jobService.getAll().stream().map(Job::getLocation).distinct().toList();
    }
}
