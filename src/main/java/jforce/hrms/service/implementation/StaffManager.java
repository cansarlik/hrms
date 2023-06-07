package jforce.hrms.service.implementation;


import jforce.hrms.service.StaffService;
import jforce.hrms.service.UserService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.helpers.EmailService;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.core.utilities.tools.StringTools;
import jforce.hrms.repository.StaffDao;
import jforce.hrms.entities.Staff;
import jforce.hrms.entities.User;
import jforce.hrms.entities.dtos.staff.StaffSaveDto;
import jforce.hrms.entities.dtos.staff.StaffUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffManager implements StaffService {
    private final StaffDao staffDao;
    private final UserService userService;
    private final EmailService emailService;
    private final String FIELD = "staff";

    @Autowired
    public StaffManager(StaffDao staffDao, UserService userService, EmailService emailService){
        this.staffDao = staffDao;
        this.userService = userService;
        this.emailService = emailService;
    }


    //Get
    public DataResult<List<Staff>> getAll() {
        return new SuccessDataResult<List<Staff>>(this.staffDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Staff> getById(int id) {
        return new SuccessDataResult<Staff>(this.staffDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<Staff> getByEmail(String email) {
        return new SuccessDataResult<Staff>(this.staffDao.getByUser_Email(email), MessageResults.dataListed(FIELD));
    }

    public Result save(StaffSaveDto staff) {
        if (StringTools.isStringNullOrEmpty(staff.getFirstName()) ||
                StringTools.isStringNullOrEmpty(staff.getLastName()) ||
                StringTools.isStringNullOrEmpty(staff.getEmail()) ||
                StringTools.isStringNullOrEmpty(staff.getPassword()) ||
                StringTools.isStringNullOrEmpty(staff.getPasswordRetry())){
            return new ErrorResult(MessageResults.emptyFields);
        }

        if(!staff.getPassword().equals(staff.getPasswordRetry())){
            return new ErrorResult(MessageResults.passwordMatchFalse);
        }

        boolean checkEmail = emailService.check(staff.getEmail()).isSuccess();
        if(!checkEmail){
            return new ErrorResult(MessageResults.isEmailFormatFalse);
        }

        User byEmail = userService.getByEmail(staff.getEmail()).getData();

        if(byEmail != null){
            return new ErrorResult(MessageResults.alreadyExists("email"));
        }

        User user = new User(
                staff.getEmail(),
                staff.getPassword(),
                true,
                "staff"
        );
        userService.save(user);

        Staff staffObject = new Staff(
                user.getId(),
                staff.getFirstName(),
                staff.getLastName()
        );

        this.staffDao.save(staffObject);

        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result updateById(StaffUpdateDto staff) {
        Result updateResult = userService.updateEmail(staff.getUserId(), staff.getEmail());
        if(!updateResult.isSuccess()){
            return new ErrorResult(updateResult.getMessage());
        }
        this.staffDao.updateById(staff);
        return new SuccessResult(MessageResults.updated(FIELD));
    }

    public Result delete(Staff staff) {
        this.staffDao.delete(staff);
        this.userService.delete(staff.getUser());
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        this.staffDao.deleteById(id);
        this.userService.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }
}
