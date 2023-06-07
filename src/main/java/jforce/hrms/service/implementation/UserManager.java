package jforce.hrms.service.implementation;

import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.core.utilities.tools.StringTools;
import jforce.hrms.repository.UserDao;
import jforce.hrms.entities.*;
import jforce.hrms.entities.dtos.user.UserLoginDto;
import jforce.hrms.entities.dtos.user.UserUpdatePasswordDto;
import jforce.hrms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private final UserDao userDao;
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final StaffService staffService;
    private final FavoriteProjectService favoriteProjectService;
    private final String FIELD = "user";

    @Autowired
    public UserManager(UserDao userDao, @Lazy EmployeeService employeeService, @Lazy CompanyService companyService, @Lazy StaffService staffService, @Lazy FavoriteProjectService favoriteProjectService){
        this.userDao = userDao;
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.staffService = staffService;
        this.favoriteProjectService = favoriteProjectService;
    }


    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(this.userDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<User> getById(int id) {
        return new SuccessDataResult<User>(this.userDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<User> getByEmail(String email) {
        User user = this.userDao.getByEmail(email);
        if(user == null){
            return new ErrorDataResult<User>(MessageResults.notFound(FIELD));
        }
        return new SuccessDataResult<User>(user, MessageResults.dataListed(FIELD));
    }

    public DataResult<?> getUserTypeByEmail(String email) {
        DataResult<Employee> findEmployee = employeeService.getByEmail(email);
        if(findEmployee.getData() != null){
            return new SuccessDataResult<Employee>(findEmployee.getData(), MessageResults.dataListed(FIELD));
        }

        DataResult<Company> findCompany = companyService.getByEmail(email);
        if(findCompany.getData() != null){
            return new SuccessDataResult<Company>(findCompany.getData(), MessageResults.dataListed(FIELD));
        }

        DataResult<Staff> findStaff = staffService.getByEmail(email);
        if(findStaff.getData() != null){
            return new SuccessDataResult<Staff>(findStaff.getData(), MessageResults.dataListed(FIELD));
        }

        return new ErrorDataResult<>(MessageResults.notFound(FIELD));
    }

    public DataResult<User> getByEmailAndPassword(String email, String password) {
        User user = this.userDao.getByEmailAndPassword(email, password);
        if(user == null){
            return new ErrorDataResult<User>(MessageResults.notFound(FIELD));
        }
        return new SuccessDataResult<User>(user, MessageResults.dataListed(FIELD));

    }

    public Result save(User user) {
        if(StringTools.isStringNullOrEmpty(user.getEmail()) ||
                StringTools.isStringNullOrEmpty(user.getPassword())){
            return new ErrorResult(MessageResults.emptyField);
        }

        this.userDao.save(user);
        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result updateVerifiedById(boolean verified, int id) {
        User user = getById(id).getData();
        if(user == null){
            return new ErrorResult(verified ? MessageResults.verificationSuccessFalse : MessageResults.unverificationSuccessFalse);
        }

        this.userDao.updateVerifiedById(verified,id);
        return new SuccessResult(verified ? MessageResults.verificationSuccessTrue: MessageResults.unverificationSuccessTrue);
    }

    public Result updateVerifiedByEmail(boolean verified, String email) {
        User user = getByEmail(email).getData();
        if(user == null){
            return new ErrorResult(verified ? MessageResults.verificationSuccessFalse : MessageResults.unverificationSuccessFalse);
        }

        this.userDao.updateVerifiedByEmail(verified,email);
        return new SuccessResult(verified ? MessageResults.verificationSuccessTrue: MessageResults.unverificationSuccessTrue);
    }

    public Result updateEmail(int id, String email) {
        User user = getById(id).getData();
        if(user.getEmail().equals(email)){
            return new SuccessResult();
        }

        User findEmail = getByEmail(email).getData();
        if(findEmail != null){
            return new ErrorResult(MessageResults.alreadyExists("email"));
        }

        this.userDao.updateEmail(id, email);
        return new SuccessResult();
    }

    public Result updatePassword(UserUpdatePasswordDto dto) {
        User user = getById(dto.getId()).getData();
        if(!user.getPassword().equals(dto.getOldPassword())){
            return new ErrorResult(MessageResults.oldPasswordMatchFalse);
        }

        if(!dto.getNewPassword().equals(dto.getNewPasswordRetry())){
            return new ErrorResult(MessageResults.newPasswordMatchFalse);
        }

        this.userDao.updatePassword(dto);
        return new SuccessResult(MessageResults.updated("password"));
    }

    public DataResult<?> login(UserLoginDto user) {
        DataResult<User> findUser = getByEmailAndPassword(user.getEmail(), user.getPassword());
        if(!findUser.isSuccess()){
            return new ErrorDataResult<>(MessageResults.errorLogin);
        }

        DataResult<Employee> findEmployee = employeeService.getByEmail(user.getEmail());
        if(findEmployee.getData() != null){
            return new SuccessDataResult<Employee>(findEmployee.getData(), MessageResults.successLogin);
        }

        DataResult<Company> findCompany = companyService.getByEmail(user.getEmail());
        if(findCompany.getData() != null){
            return new SuccessDataResult<Company>(findCompany.getData(), MessageResults.successLogin);
        }

        DataResult<Staff> findStaff = staffService.getByEmail(user.getEmail());
        if(findStaff.getData() != null){
            return new SuccessDataResult<Staff>(findStaff.getData(), MessageResults.successLogin);
        }

        return new ErrorDataResult<>(MessageResults.errorLogin);
    }

    public Result delete(User user) {
        List<FavoriteProject> favoriteProjects = favoriteProjectService.getByUserId(user.getId()).getData();
        favoriteProjectService.deleteAll(favoriteProjects);
        this.userDao.delete(user);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        List<FavoriteProject> favoriteProjects = favoriteProjectService.getByUserId(id).getData();
        favoriteProjectService.deleteAll(favoriteProjects);
        this.userDao.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }
}
