package jforce.hrms.service.implementation;

import jforce.hrms.service.CompanyService;
import jforce.hrms.service.ProjectAnnounceService;
import jforce.hrms.service.UserService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.helpers.EmailService;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.core.utilities.tools.StringTools;
import jforce.hrms.repository.CompanyDao;
import jforce.hrms.entities.Company;
import jforce.hrms.entities.ProjectAnnounce;
import jforce.hrms.entities.User;
import jforce.hrms.entities.dtos.company.CompanySaveDto;
import jforce.hrms.entities.dtos.company.CompanyUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyManager implements CompanyService {
    private final CompanyDao companyDao;
    private final UserService userService;
    private final ProjectAnnounceService projectAnnounceService;
    private final EmailService emailService;
    private final String FIELD = "company";

    @Autowired
    public CompanyManager(CompanyDao companyDao, UserService userService, ProjectAnnounceService projectAnnounceService, EmailService emailService){
        super();
        this.companyDao = companyDao;
        this.userService = userService;
        this.projectAnnounceService = projectAnnounceService;
        this.emailService = emailService;
    }

    public DataResult<List<Company>> getAll() {
        return new SuccessDataResult<List<Company>>(this.companyDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Company> getById(int id) {
        return new SuccessDataResult<Company>(this.companyDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<Company> getByEmail(String email) {
        return new SuccessDataResult<Company>(this.companyDao.getByUser_Email(email), MessageResults.dataListed(FIELD));
    }

    public Result save(CompanySaveDto company) {
        //Field Check
        if(StringTools.isStringNullOrEmpty(company.getCompanyName()) ||
                StringTools.isStringNullOrEmpty(company.getWebsite()) ||
                StringTools.isStringNullOrEmpty(company.getPhone()) ||
                StringTools.isStringNullOrEmpty(company.getEmail()) ||
                StringTools.isStringNullOrEmpty(company.getPassword()) ||
                StringTools.isStringNullOrEmpty(company.getPasswordRetry())){
            return new ErrorResult(MessageResults.emptyFields);
        }


        if(!company.getPassword().equals(company.getPasswordRetry())){
            return new ErrorResult(MessageResults.passwordMatchFalse);
        }

        //Check Email Format
        boolean checkEmail = emailService.checkWithDomain(company.getEmail(), company.getWebsite()).isSuccess();
        if(!checkEmail){
            return new ErrorResult(MessageResults.isEmailFormatFalse);
        }

       User byEmail = userService.getByEmail(company.getEmail()).getData();

        if(byEmail != null){
            return new ErrorResult(MessageResults.alreadyExists("email"));
        }

        User user = new User(
                company.getEmail(),
                company.getPassword(),
                false,
                "company"
        );
        userService.save(user);

        Company companyObject = new Company(
                user.getId(),
                company.getCompanyName(),
                company.getWebsite(),
                company.getPhone(),
                false
        );
        this.companyDao.save(companyObject);
        return new SuccessResult(MessageResults.saved(FIELD, MessageResults.validateEmailBySystem));
    }

    public Result updateById(CompanyUpdateDto company) {
        Result updateResult = userService.updateEmail(company.getUserId(), company.getEmail());
        if(!updateResult.isSuccess()){
            return new ErrorResult(updateResult.getMessage());
        }
        this.companyDao.updateById(company);
        return new SuccessResult(MessageResults.updated(FIELD));
    }

    public Result delete(Company company) {
        List<ProjectAnnounce> projectAnnounces = projectAnnounceService.getAllByCompanyId(company.getUserId()).getData();
        if(projectAnnounces != null || projectAnnounces.size() > 0){
            for(ProjectAnnounce j : projectAnnounces){
                projectAnnounceService.deleteById(j.getId());
            }
        }

        this.companyDao.delete(company);
        this.userService.delete(company.getUser());

        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        List<ProjectAnnounce> projectAnnounces = projectAnnounceService.getAllByCompanyId(id).getData();
        if(projectAnnounces != null || projectAnnounces.size() > 0){
            for(ProjectAnnounce j : projectAnnounces){
                projectAnnounceService.deleteById(j.getId());
            }
        }

        this.companyDao.deleteById(id);
        this.userService.deleteById(id);

        return new SuccessResult(MessageResults.deleted(FIELD));
    }
}
