package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Office;
import jforce.hrms.entities.dtos.office.OfficeSaveDto;
import jforce.hrms.entities.dtos.office.OfficeSaveMultipleDto;

import java.util.List;

public interface OfficeService {
    //Get
    DataResult<List<Office>> getAll();
    DataResult<Office> getById(int id);
    DataResult<Office> getByOfficeName(String officeName);

    //Post
    Result save(OfficeSaveDto office);
    Result saveMultiple(OfficeSaveMultipleDto office);

    //Delete
    Result delete(Office office);
    Result deleteById(int id);

}