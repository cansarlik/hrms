package jforce.hrms.api;

import jforce.hrms.service.CompanyService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.ErrorDataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Company;
import jforce.hrms.entities.dtos.company.CompanySaveDto;
import jforce.hrms.entities.dtos.company.CompanyUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin
public class CompaniesController {
    private CompanyService companyService;

    @Autowired
    public CompaniesController(CompanyService companyService){
        this.companyService = companyService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<Company>> getAll(){
        return this.companyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") int id){
        return ResponseEntity.ok(this.companyService.getById(id));
    }

    @GetMapping("/getByEmail")
    public DataResult<Company> getByEmail(@RequestParam(value = "email") String email) {
        return this.companyService.getByEmail(email);
    }

    //Post
    @PostMapping("")
    public Result save(@RequestBody CompanySaveDto company) {
        return this.companyService.save(company);
    }

    //Put
    @PutMapping("")
    public Result updateById(@RequestBody CompanyUpdateDto company){
        return this.companyService.updateById(company);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody Company company){
        return this.companyService.delete(company);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.companyService.deleteById(id);
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
