package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Ability;
import jforce.hrms.entities.dtos.ability.AbilitySaveDto;
import jforce.hrms.entities.dtos.ability.AbilityUpdateDto;

import java.util.List;

public interface AbilityService {
    //Get
    DataResult<List<Ability>> getAll();
    DataResult<List<Ability>> getAllByInformationId(int informationId);
    DataResult<Ability> getById(int id);
    DataResult<List<Ability>> getByIds(List<Integer> ids);

    //Post
    Result save(AbilitySaveDto ability);

    //Put
    Result updateById(AbilityUpdateDto ability);

    //Delete
    Result delete(Ability ability);
    Result deleteById(int id);
    Result deleteByIds(List<Integer> ids);
    Result deleteAll(List<Ability> abilities);
}
