package jforce.hrms.service.implementation;

import jforce.hrms.service.ProjectExperienceService;
import jforce.hrms.service.InformationService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.core.utilities.tools.StringTools;
import jforce.hrms.repository.ProjectExperienceDao;
import jforce.hrms.entities.ProjectExperience;
import jforce.hrms.entities.Information;
import jforce.hrms.entities.dtos.projectExperience.ProjectExperienceSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectExperienceManager implements ProjectExperienceService {
    private final ProjectExperienceDao projectExperienceDao;
    private final InformationService informationService;
    private final String FIELD = "projectExperience";

    @Autowired
    private ProjectExperienceManager(ProjectExperienceDao projectExperienceDao, InformationService informationService){
        this.projectExperienceDao = projectExperienceDao;
        this.informationService = informationService;
    }

    public DataResult<List<ProjectExperience>> getAll() {
        return new SuccessDataResult<List<ProjectExperience>>(this.projectExperienceDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectExperience>> getAllByInformationId(int informationId) {
        return new SuccessDataResult<List<ProjectExperience>>(this.projectExperienceDao.getAllByInformation_Id(informationId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<ProjectExperience> getById(int id) {
        return new SuccessDataResult<ProjectExperience>(this.projectExperienceDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<List<ProjectExperience>> getByIds(List<Integer> ids) {
        List<ProjectExperience> projectExperiences = new ArrayList<>();

        for(var id : ids){
            DataResult<ProjectExperience> projectExperienceDataResult = getById(id);
            if(projectExperienceDataResult.isSuccess()){
                projectExperiences.add(projectExperienceDataResult.getData());
            }
        }

        return new SuccessDataResult<List<ProjectExperience>>(projectExperiences, MessageResults.allDataListed(FIELD));
    }

    public Result save(ProjectExperienceSaveDto projectExperience) {
        if(StringTools.isStringNullOrEmpty(projectExperience.getCompanyName()) ||
                StringTools.isStringNullOrEmpty(projectExperience.getTitleName()) ||
                StringTools.isStringNullOrEmpty(String.valueOf(projectExperience.getStartYear()))){
            return new ErrorResult(MessageResults.emptyField);
        }
        DataResult<Information> information = informationService.getById(projectExperience.getInformationId());

        if(!information.isSuccess()){
            return new ErrorResult(MessageResults.notFound(FIELD));
        }

        ProjectExperience projectExperienceObject = new ProjectExperience(
                information.getData(),
                projectExperience.getCompanyName(),
                projectExperience.getTitleName(),
                projectExperience.getStartYear(),
                projectExperience.getEndYear()
        );

        this.projectExperienceDao.save(projectExperienceObject);
        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result delete(ProjectExperience projectExperience) {
        this.projectExperienceDao.delete(projectExperience);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        this.projectExperienceDao.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteByIds(List<Integer> ids) {
        for(int id: ids){
            this.projectExperienceDao.deleteById(id);
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }

    public Result deleteAll(List<ProjectExperience> projectExperiences) {
        for(var projectExperince: projectExperiences){
            this.projectExperienceDao.deleteById(projectExperince.getId());
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }
}
