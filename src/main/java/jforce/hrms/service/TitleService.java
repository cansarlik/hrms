package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Title;
import jforce.hrms.entities.dtos.title.TitleSaveDto;

import java.util.List;

public interface TitleService {
    //Get
    DataResult<List<Title>> getAll();
    DataResult<Title> getById(int id);
    DataResult<Title> getByTitleName(String titleName);

    //Save
    Result save(TitleSaveDto title);

    //Delete
    Result delete(Title title);
    Result deleteById(int id);
}
