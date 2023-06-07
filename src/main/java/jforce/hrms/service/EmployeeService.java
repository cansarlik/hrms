package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Employee;
import jforce.hrms.entities.dtos.employee.EmployeeSaveDto;
import jforce.hrms.entities.dtos.employee.EmployeeUpdateDto;

import java.util.List;

public interface EmployeeService {
    //Get
    DataResult<List<Employee>> getAll();
    DataResult<Employee> getById(int id);
    DataResult<Employee> getByEmail(String email);
    DataResult<Employee> getByIdentityNo(String tcNo);

    //Post
    Result save(EmployeeSaveDto employee);

    //Put
    Result updateById(EmployeeUpdateDto employee);

    //Delete
    Result delete(Employee employee);
    Result deleteById(int id);
}
