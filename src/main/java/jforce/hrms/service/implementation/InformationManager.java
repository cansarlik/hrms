package jforce.hrms.service.implementation;

import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.repository.InformationDao;
import jforce.hrms.entities.Employee;
import jforce.hrms.entities.Information;
import jforce.hrms.entities.dtos.information.InformationSaveDto;
import jforce.hrms.entities.dtos.information.InformationUpdateDto;
import jforce.hrms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationManager implements InformationService {
    private final InformationDao informationDao;
    private final EmployeeService employeeService;

    private final AbilityService abilityService;
    private final ImageService imageService;
    private final InventoryService inventoryService;
    private final ProjectExperienceService projectExperienceService;
    private final LanguageService languageService;

    private final String FIELD = "information";

    @Autowired
    public InformationManager(@Lazy InformationDao informationDao, @Lazy EmployeeService employeeService, @Lazy LanguageService languageService, @Lazy AbilityService abilityService, @Lazy InventoryService inventoryService, @Lazy ImageService imageService, @Lazy ProjectExperienceService projectExperienceService) {
        this.informationDao = informationDao;
        this.employeeService = employeeService;
        this.languageService = languageService;
        this.abilityService = abilityService;
        this.inventoryService = inventoryService;
        this.imageService = imageService;
        this.projectExperienceService = projectExperienceService;
    }


    public DataResult<List<Information>> getAll() {
        return new SuccessDataResult<List<Information>>(this.informationDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Information> getById(int id) {
        return new SuccessDataResult<Information>(this.informationDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<Information> getByEmployeeId(int employeeId) {
        return new SuccessDataResult<Information>(this.informationDao.getByEmployee_UserId(employeeId), MessageResults.dataListed(FIELD));
    }

    public Result save(InformationSaveDto information) {
        DataResult<Employee> employee = employeeService.getById(information.getEmployeeId());

        Information informationObject = new Information(
                employee.getData(),
                information.getGithubUrl(),
                information.getLinkedinUrl(),
                information.getDescription()
        );

        this.informationDao.save(informationObject);

        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result updateById(InformationUpdateDto information) {
        this.informationDao.updateById(information.getId(),information.getDescription(),information.getGithubUrl(),information.getLinkedinUrl());
        return new SuccessResult(MessageResults.updated(FIELD));
    }

    public Result delete(Information information) {
        this.informationDao.delete(information);
        this.abilityService.deleteAll(information.getAbilities());
        this.imageService.deleteAll(information.getImages());
        this.inventoryService.deleteAll(information.getInventories());
        this.projectExperienceService.deleteAll(information.getProjectExperiences());
        this.languageService.deleteAll(information.getLanguages());
        return new SuccessResult(MessageResults.deleted(FIELD));
    }


    public Result deleteById(int id) {
        DataResult<Information> information = getById(id);
        if(!information.isSuccess()){
            return new ErrorResult(MessageResults.notFound(FIELD));
        }

        this.abilityService.deleteAll(information.getData().getAbilities());
        this.imageService.deleteAll(information.getData().getImages());
        this.inventoryService.deleteAll(information.getData().getInventories());
        this.projectExperienceService.deleteAll(information.getData().getProjectExperiences());
        this.languageService.deleteAll(information.getData().getLanguages());
        this.informationDao.delete(information.getData());
        return new SuccessResult(MessageResults.deleted(FIELD));
    }
}

