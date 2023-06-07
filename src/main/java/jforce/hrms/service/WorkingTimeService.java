package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.WorkingTime;
import jforce.hrms.entities.dtos.workingTime.WorkingTimeSaveDto;

import java.util.List;

public interface WorkingTimeService {
    //Get
    DataResult<List<WorkingTime>> getAll();
    DataResult<WorkingTime> getById(int id);
    DataResult<WorkingTime> getByWorkingTimeName(String workingTimeName);

    //Save
    Result save(WorkingTimeSaveDto workingTime);

    //Delete
    Result delete(WorkingTime workingTime);
    Result deleteById(int id);
}