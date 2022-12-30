package nhathm.jobhuntbe.controller;

import jakarta.validation.Valid;
import nhathm.jobhuntbe.domain.model.Company;
import nhathm.jobhuntbe.domain.model.User;
import nhathm.jobhuntbe.payload.request.CreateCompanyRequest;
import nhathm.jobhuntbe.service.CompanyService;
import nhathm.jobhuntbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nhathm
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Company createCompany(@RequestBody @Valid CreateCompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setLogoUrl(request.getLogoUrl());
        company.setLocation(request.getLocation());
        company.setMarkets(request.getMarkets());
        company.setCompanySize(request.getCompanySize());
        company.setDescription(request.getDescription());
        company = companyService.save(company);

        User user = userService.findById(request.getUserId());
        user.setCompanyRole(request.getCompanyRoleOfUser());
        user.setCompanyId(company.getId());
        userService.save(user);

        return company;
    }
}
