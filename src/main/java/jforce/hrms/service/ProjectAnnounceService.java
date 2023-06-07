package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.ProjectAnnounce;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceFilterDto;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceSaveDto;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceUpdateDto;

import java.util.List;

public interface ProjectAnnounceService {
    //Post
    Result save(ProjectAnnounceSaveDto projectAnnounce);

    //Put
    Result updateActive(boolean active, int id);
    Result updateConfirmed(boolean confirmed, int id);
    Result updateById(ProjectAnnounceUpdateDto projectAnnounce);

    //Get
    DataResult<List<ProjectAnnounce>> getAll();
    DataResult<List<ProjectAnnounce>> getAll(int pageNo, int pageSize);
    DataResult<List<ProjectAnnounce>> getAllOrderByReleaseDateDesc();
    DataResult<List<ProjectAnnounce>> getAllByCompanyId(int companyId);
    DataResult<List<ProjectAnnounce>> getAllByCompanyIdOrderByReleaseDateAsc(int companyId);
    DataResult<List<ProjectAnnounce>> getAllByCompanyIdOrderByReleaseDateDesc(int companyId);

    DataResult<List<ProjectAnnounce>> getByActiveTrue();
    DataResult<List<ProjectAnnounce>> getByConfirmedTrue();
    DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrue();
    DataResult<List<ProjectAnnounce>> getByActiveTrueOrderByReleaseDate();
    DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrueOrderByReleaseDateAsc();
    DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrueOrderByReleaseDateDesc();

    DataResult<List<ProjectAnnounce>> getByActiveTrueAndCompanyIdOrderByReleaseDate(int companyId);
    DataResult<List<ProjectAnnounce>> getByActiveTrueAndCompanyId(int companyId);

    DataResult<List<ProjectAnnounce>> getByFilter(ProjectAnnounceFilterDto filter, int pageNo, int pageSize);
    DataResult<ProjectAnnounce> getById(int id);

    //Delete
    Result delete(ProjectAnnounce projectAnnounce);
    Result deleteById(int id);
}
