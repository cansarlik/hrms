package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Information;
import jforce.hrms.entities.dtos.information.InformationSaveDto;
import jforce.hrms.entities.dtos.information.InformationUpdateDto;

import java.util.List;

public interface InformationService {
    //Get
    DataResult<List<Information>> getAll();
    DataResult<Information> getById(int id);
    DataResult<Information> getByEmployeeId(int employeeId);

    //Post
    Result save(InformationSaveDto information);

    //Put
    Result updateById(InformationUpdateDto information);

    //Delete
    Result delete(Information information);
    Result deleteById(int id);
}
