package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Language;
import jforce.hrms.entities.dtos.language.LanguageSaveDto;

import java.util.List;

public interface LanguageService {
    //Get
    DataResult<List<Language>> getAll();
    DataResult<List<Language>> getAllByInformationId(int informationId);
    DataResult<Language> getById(int id);
    DataResult<List<Language>> getByIds(List<Integer> ids);

    //Post
    Result save(LanguageSaveDto language);

    //Delete
    Result delete(Language language);
    Result deleteById(int id);
    Result deleteByIds(List<Integer> ids);
    Result deleteAll(List<Language> languages);
}