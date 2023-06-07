package jforce.hrms.service.implementation;

import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.core.utilities.tools.StringTools;
import jforce.hrms.repository.ProjectAnnounceDao;
import jforce.hrms.entities.FavoriteProject;
import jforce.hrms.entities.ProjectAnnounce;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceFilterDto;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceSaveDto;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceUpdateDto;
import jforce.hrms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectAnnounceManager implements ProjectAnnounceService {
    private final ProjectAnnounceDao projectAnnounceDao;
    private final TitleService titleService;
    private final OfficeService officeService;
    private final WorkingTimeService workingTimeService;
    private final CompanyService companyService;
    private final FavoriteProjectService favoriteProjectService;


    private final String FIELD = "projectAnnounce";

    @Autowired
    public ProjectAnnounceManager(ProjectAnnounceDao projectAnnounceDao, TitleService titleService, OfficeService officeService, WorkingTimeService workingTimeService, @Lazy CompanyService companyService, @Lazy FavoriteProjectService favoriteProjectService){
        this.projectAnnounceDao = projectAnnounceDao;
        this.titleService = titleService;
        this.officeService = officeService;
        this.workingTimeService = workingTimeService;
        this.companyService = companyService;
        this.favoriteProjectService = favoriteProjectService;
    }

    //Post
    public Result save(ProjectAnnounceSaveDto projectAnnounce) {
        if (StringTools.isStringNullOrEmpty(projectAnnounce.getDescription()) ||
                StringTools.isStringNullOrEmpty(String.valueOf(projectAnnounce.getOpenTitlesAmount())) ||
                StringTools.isStringNullOrEmpty(String.valueOf(projectAnnounce.getOfficeId())) ||
                StringTools.isStringNullOrEmpty(String.valueOf(projectAnnounce.getTitleId())) ||
                StringTools.isStringNullOrEmpty(String.valueOf(projectAnnounce.getDeadline()))) {
            return new ErrorResult(MessageResults.emptyFields);
        }

        ProjectAnnounce projectAnnounceObject= new ProjectAnnounce(
                projectAnnounce.getDescription(),
                new Date(System.currentTimeMillis()),
                projectAnnounce.getDeadline(),
                projectAnnounce.getMinSalary(),
                projectAnnounce.getMaxSalary(),
                projectAnnounce.getOpenTitlesAmount(),
                true,
                false
        );

        projectAnnounceObject.setOffice(officeService.getById(projectAnnounce.getOfficeId()).getData());
        projectAnnounceObject.setWorkingTime(workingTimeService.getById(projectAnnounce.getWorkingTimeId()).getData());
        projectAnnounceObject.setTitle(titleService.getById(projectAnnounce.getTitleId()).getData());
        projectAnnounceObject.setCompany(companyService.getById(projectAnnounce.getCompanyId()).getData());

        this.projectAnnounceDao.save(projectAnnounceObject);
        return new SuccessResult(MessageResults.saved(FIELD));
    }


    //Put
    public Result updateActive(boolean active, int id) {
        this.projectAnnounceDao.updateActive(active, id);
        return new SuccessResult(MessageResults.updated(FIELD));
    }

    public Result updateConfirmed(boolean confirmed, int id) {
        this.projectAnnounceDao.updateConfirmed(confirmed, id);
        return new SuccessResult(MessageResults.updated(FIELD));
    }

    public Result updateById(ProjectAnnounceUpdateDto projectAnnounce) {
        //Office office = officeService.getById(projectAnnounce.getOfficeId()).getData();
        //WorkingTime workingTime = workingTimeService.getById(projectAnnounce.getWorkingTimeId()).getData();
        //Title title = titleService.getById(projectAnnounce.getTitleId()).getData();

        this.projectAnnounceDao.updateById(projectAnnounce);
        return new SuccessResult(MessageResults.updated(FIELD));
    }


    //Get
    public DataResult<List<ProjectAnnounce>> getAll() {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.findAll(pageable).getContent(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getAllOrderByReleaseDateDesc() {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getAllByOrderByReleaseDateDesc(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getAllByCompanyId(int companyId) {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getAllByCompany_UserId(companyId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getAllByCompanyIdOrderByReleaseDateAsc(int companyId) {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getAllByCompany_UserIdOrderByReleaseDateAsc(companyId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getAllByCompanyIdOrderByReleaseDateDesc(int companyId) {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getAllByCompany_UserIdOrderByReleaseDateDesc(companyId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getByActiveTrue() {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getByActiveTrue(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getByConfirmedTrue() {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getByConfirmedTrue(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrue() {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getByActiveTrueAndConfirmedTrue(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getByActiveTrueOrderByReleaseDate() {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getByActiveTrueOrderByReleaseDateDesc(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrueOrderByReleaseDateAsc() {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getByActiveTrueAndConfirmedTrueOrderByReleaseDateAsc(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrueOrderByReleaseDateDesc() {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getByActiveTrueAndConfirmedTrueOrderByReleaseDateDesc(), MessageResults.allDataListed(FIELD));
    }



    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndCompanyIdOrderByReleaseDate(int companyId) {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getByActiveTrueAndCompany_UserIdOrderByReleaseDateDesc(companyId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndCompanyId(int companyId) {
        return new SuccessDataResult<List<ProjectAnnounce>>(this.projectAnnounceDao.getByActiveTrueAndCompany_UserId(companyId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<ProjectAnnounce>> getByFilter(ProjectAnnounceFilterDto filter, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        var data = this.projectAnnounceDao.getByFilter(filter, pageable);
        return new SuccessDataResult<List<ProjectAnnounce>>(data.getContent(), String.valueOf(data.getTotalPages()));
    }


    public DataResult<ProjectAnnounce> getById(int id) {
        return new SuccessDataResult<ProjectAnnounce>(this.projectAnnounceDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public Result delete(ProjectAnnounce projectAnnounce) {
        List<FavoriteProject> favoriteProjects = favoriteProjectService.getByProjectAnnounceId(projectAnnounce.getId()).getData();
        favoriteProjectService.deleteAll(favoriteProjects);
        this.projectAnnounceDao.delete(projectAnnounce);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        List<FavoriteProject> favoriteProjects = favoriteProjectService.getByProjectAnnounceId(id).getData();
        favoriteProjectService.deleteAll(favoriteProjects);
        this.projectAnnounceDao.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }
}
