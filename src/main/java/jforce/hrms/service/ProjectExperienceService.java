package jforce.hrms.service;


import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.ProjectExperience;
import jforce.hrms.entities.dtos.projectExperience.ProjectExperienceSaveDto;

import java.util.List;

public interface ProjectExperienceService {
    //Get
    DataResult<List<ProjectExperience>> getAll();
    DataResult<List<ProjectExperience>> getAllByInformationId(int informationId);
    DataResult<ProjectExperience> getById(int id);
    DataResult<List<ProjectExperience>> getByIds(List<Integer> ids);

    //Post
    Result save(ProjectExperienceSaveDto projectExperience);

    //Delete
    Result delete(ProjectExperience projectExperience);
    Result deleteById(int id);
    Result deleteByIds(List<Integer> ids);
    Result deleteAll(List<ProjectExperience> projectExperiences);
}