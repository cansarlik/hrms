package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Company;
import jforce.hrms.entities.dtos.company.CompanySaveDto;
import jforce.hrms.entities.dtos.company.CompanyUpdateDto;

import java.util.List;

public interface CompanyService {
    //Get
    DataResult<List<Company>> getAll();
    DataResult<Company> getById(int id);
    DataResult<Company> getByEmail(String email);

    //Post
    Result save(CompanySaveDto company);

    //Put
    Result updateById(CompanyUpdateDto company);

    //Delete
    Result delete(Company company);
    Result deleteById(int id);

}
