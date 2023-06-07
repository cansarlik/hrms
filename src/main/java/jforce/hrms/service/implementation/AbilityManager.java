package jforce.hrms.service.implementation;

import jforce.hrms.service.AbilityService;
import jforce.hrms.service.InformationService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.repository.AbilityDao;
import jforce.hrms.entities.Ability;
import jforce.hrms.entities.Information;
import jforce.hrms.entities.dtos.ability.AbilitySaveDto;
import jforce.hrms.entities.dtos.ability.AbilityUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbilityManager implements AbilityService {

    private final AbilityDao abilityDao;
    private final InformationService informationService;
    private final String FIELD = "ability";


    @Autowired
    public AbilityManager(AbilityDao abilityDao, InformationService informationService){
        this.abilityDao = abilityDao;
        this.informationService = informationService;
    }

    public DataResult<List<Ability>> getAll() {
        return new SuccessDataResult<List<Ability>>(this.abilityDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<Ability>> getAllByInformationId(int informationId) {
        return new SuccessDataResult<List<Ability>>(this.abilityDao.getAllByInformation_Id(informationId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Ability> getById(int id) {
        return new SuccessDataResult<Ability>(this.abilityDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<List<Ability>> getByIds(List<Integer> ids) {
        List<Ability> abilities = new ArrayList<>();

        for(var id : ids){
            DataResult<Ability> abilityDataResult = getById(id);
            if(abilityDataResult.isSuccess()){
               abilities.add(abilityDataResult.getData());
            }
        }

        return new SuccessDataResult<List<Ability>>(abilities, MessageResults.allDataListed(FIELD));
    }

    public Result save(AbilitySaveDto ability) {
        DataResult<Information> information = informationService.getById(ability.getInformationId());

        if(!information.isSuccess()){
            return new ErrorResult(MessageResults.notFound(FIELD));
        }

        Ability abilityObject = new Ability(
            information.getData(),
            ability.getAbilityName()
        );

        this.abilityDao.save(abilityObject);
        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result updateById(AbilityUpdateDto ability) {
        this.abilityDao.updateById(ability.getId(), ability.getAbilityName());
        return new SuccessResult(MessageResults.updated(FIELD));
    }

    public Result delete(Ability ability) {
        this.abilityDao.delete(ability);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        this.abilityDao.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteByIds(List<Integer> ids) {
        for (int id : ids){
            this.abilityDao.deleteById(id);
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }

    public Result deleteAll(List<Ability> abilities) {
        for (var ability : abilities){
            this.abilityDao.deleteById(ability.getId());
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }
}
