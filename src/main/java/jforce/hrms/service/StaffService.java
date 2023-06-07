package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Staff;
import jforce.hrms.entities.dtos.staff.StaffSaveDto;
import jforce.hrms.entities.dtos.staff.StaffUpdateDto;

import java.util.List;

public interface StaffService {
    //Get
    DataResult<List<Staff>> getAll();
    DataResult<Staff> getById(int id);
    DataResult<Staff> getByEmail(String email);

    //Post
    Result save(StaffSaveDto staff);

    //Put
    Result updateById(StaffUpdateDto staff);

    //Delete
    Result delete(Staff staff);
    Result deleteById(int id);
}
