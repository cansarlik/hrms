package jforce.hrms.api;

import jforce.hrms.service.StaffService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.ErrorDataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Staff;
import jforce.hrms.entities.dtos.staff.StaffSaveDto;
import jforce.hrms.entities.dtos.staff.StaffUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/staffs")
@CrossOrigin
public class StaffsController {
    private StaffService staffService;

    @Autowired
    public StaffsController(StaffService staffService){
        this.staffService = staffService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<Staff>> getAll(){
        return this.staffService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") int id){
        return ResponseEntity.ok(this.staffService.getById(id));
    }

    @GetMapping("/getByEmail")
    public DataResult<Staff> getByEmail(@PathVariable(value = "email") String email){
        return this.staffService.getByEmail(email);
    }

    //Post
    @PostMapping("")
    public Result save(@Valid @RequestBody StaffSaveDto staff){
        return this.staffService.save(staff);
    }

    //Put
    @PutMapping("")
    public Result updateById(@RequestBody StaffUpdateDto staff){
        return this.staffService.updateById(staff);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody Staff staff){
        return this.staffService.delete(staff);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.staffService.deleteById(id);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String, String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, MessageResults.error);
        return errors;
    }
}
