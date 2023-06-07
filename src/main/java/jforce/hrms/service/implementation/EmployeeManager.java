package jforce.hrms.service.implementation;

import jforce.hrms.service.EmployeeService;
import jforce.hrms.service.TitleService;
import jforce.hrms.service.InformationService;
import jforce.hrms.service.UserService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.adapters.abstracts.UserCheckService;
import jforce.hrms.core.adapters.concretes.FakeMernisServiceAdapter;
import jforce.hrms.core.utilities.helpers.EmailService;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.core.utilities.tools.StringTools;
import jforce.hrms.repository.EmployeeDao;
import jforce.hrms.entities.Employee;
import jforce.hrms.entities.Title;
import jforce.hrms.entities.Information;
import jforce.hrms.entities.User;
import jforce.hrms.entities.dtos.employee.EmployeeSaveDto;
import jforce.hrms.entities.dtos.employee.EmployeeUpdateDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final UserService userService;
    private final InformationService informationService;
    private final TitleService titleService;
    private final UserCheckService userCheckService = new FakeMernisServiceAdapter();
    private final EmailService emailService;
    private final String FIELD = "employee";

    public EmployeeManager(EmployeeDao employeeDao, TitleService titleService, UserService userService, InformationService informationService, EmailService emailService){
        super();
        this.employeeDao = employeeDao;
        this.titleService = titleService;
        this.userService = userService;
        this.informationService = informationService;
        this.emailService = emailService;
    }

    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Employee> getById(int id) {
        return new SuccessDataResult<Employee>(this.employeeDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<Employee> getByEmail(String email) {
        return new SuccessDataResult<Employee>(this.employeeDao.getByUser_Email(email));
    }

    public DataResult<Employee> getByIdentityNo(String identityNo) {
        return new SuccessDataResult<Employee>(this.employeeDao.getByIdentityNo(identityNo));
    }

    public Result save(EmployeeSaveDto employee) {
        //Field Check
        if (StringTools.isStringNullOrEmpty(employee.getFirstName()) ||
            StringTools.isStringNullOrEmpty(employee.getLastName()) ||
            StringTools.isStringNullOrEmpty(employee.getIdentityNo()) ||
            StringTools.isStringNullOrEmpty(String.valueOf(employee.getBirthYear())) ||
            StringTools.isStringNullOrEmpty(employee.getEmail()) ||
            StringTools.isStringNullOrEmpty(employee.getPassword()) ||
            StringTools.isStringNullOrEmpty(employee.getPasswordRetry()) ||
            StringTools.isStringNullOrEmpty(String.valueOf(employee.getTitleId()))){
            return new ErrorResult(MessageResults.emptyFields);
        }

        if(!employee.getPassword().equals(employee.getPasswordRetry())){
            return new ErrorResult(MessageResults.passwordMatchFalse);
        }

        //Check if real person
        boolean checkRealPerson = userCheckService.isRealPerson(employee.getIdentityNo(),employee.getFirstName(),employee.getLastName(),employee.getBirthYear());
        if(!checkRealPerson){
            return new ErrorResult(MessageResults.isRealPersonFalse);
        }


        //Check Email Format
        boolean checkEmail = emailService.check(employee.getEmail()).isSuccess();
        if(!checkEmail){
            return new ErrorResult(MessageResults.isEmailFormatFalse);
        }

        User byEmail = userService.getByEmail(employee.getEmail()).getData();
        Employee byIdentityNo = getByIdentityNo(employee.getIdentityNo()).getData();

        if(byEmail != null){
            return new ErrorResult(MessageResults.alreadyExists("email"));
        }

        if(byIdentityNo != null){
            return new ErrorResult(MessageResults.alreadyExists("identityNo"));
        }

        User user = new User(
                employee.getEmail(),
                employee.getPassword(),
                false,
                "employee"
        );
        userService.save(user);

        Title title = titleService.getById(employee.getTitleId()).getData();

        Employee employeeObject = new Employee(
                user.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getIdentityNo(),
                employee.getBirthYear(),
                title
        );
        this.employeeDao.save(employeeObject);

        return new SuccessResult(MessageResults.saved(FIELD, MessageResults.validateEmail));
    }

    public Result updateById(EmployeeUpdateDto employee) {
        Result updateResult = userService.updateEmail(employee.getUserId(), employee.getEmail());
        if(!updateResult.isSuccess()){
            return new ErrorResult(updateResult.getMessage());
        }
        this.employeeDao.updateById(employee);
        return new SuccessResult(MessageResults.updated(FIELD));
    }

    public Result delete(Employee employee) {
        Information information = informationService.getByEmployeeId(employee.getUserId()).getData();
        if(information != null){
            informationService.deleteById(information.getId());
        }

        this.employeeDao.delete(employee);
        this.userService.delete(employee.getUser());

        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        Information information = informationService.getByEmployeeId(id).getData();
        if(information != null){
            informationService.deleteById(information.getId());
        }

        this.employeeDao.deleteById(id);
        this.userService.deleteById(id);


        return new SuccessResult(MessageResults.deleted(FIELD));
    }
}
