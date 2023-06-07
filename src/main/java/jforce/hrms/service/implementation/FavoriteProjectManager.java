package jforce.hrms.service.implementation;

import jforce.hrms.service.FavoriteProjectService;
import jforce.hrms.service.ProjectAnnounceService;
import jforce.hrms.service.UserService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.core.utilities.results.SuccessDataResult;
import jforce.hrms.core.utilities.results.SuccessResult;
import jforce.hrms.repository.FavoriteProjectDao;
import jforce.hrms.entities.FavoriteProject;
import jforce.hrms.entities.ProjectAnnounce;
import jforce.hrms.entities.User;
import jforce.hrms.entities.dtos.favoriteProject.FavoriteProjectSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteProjectManager implements FavoriteProjectService {
    private final FavoriteProjectDao favoriteProjectDao;
    private final UserService userService;
    private final ProjectAnnounceService projectAnnounceService;

    private final String FIELD = "favoriteProject";

    @Autowired
    public FavoriteProjectManager(FavoriteProjectDao favoriteProjectDao, UserService userService, ProjectAnnounceService projectAnnounceService){
        this.favoriteProjectDao = favoriteProjectDao;
        this.userService = userService;
        this.projectAnnounceService = projectAnnounceService;
    }

    public DataResult<List<FavoriteProject>> getAll() {
        return new SuccessDataResult<List<FavoriteProject>>(this.favoriteProjectDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<FavoriteProject> getById(int id) {
        return new SuccessDataResult<FavoriteProject>(this.favoriteProjectDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<List<FavoriteProject>> getByProjectAnnounceId(int id) {
        return new SuccessDataResult<List<FavoriteProject>>(this.favoriteProjectDao.getByProjectAnnounce_Id(id), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<FavoriteProject>> getByUserId(int id) {
        return new SuccessDataResult<List<FavoriteProject>>(this.favoriteProjectDao.getByUser_Id(id), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<FavoriteProject>> getByUserEmail(String email) {
        return new SuccessDataResult<List<FavoriteProject>>(this.favoriteProjectDao.getByUser_Email(email), MessageResults.allDataListed(FIELD));
    }

    public DataResult<FavoriteProject> save(FavoriteProjectSaveDto dto) {
        User user = userService.getById(dto.getUserId()).getData();
        ProjectAnnounce projectAnnounce = projectAnnounceService.getById(dto.getProjectAnnounceId()).getData();
        FavoriteProject favoriteProject = new FavoriteProject(user, projectAnnounce);
        return new SuccessDataResult(this.favoriteProjectDao.save(favoriteProject), MessageResults.projectAddedFavorite);
    }

    public Result delete(FavoriteProject favoriteProject) {
        this.favoriteProjectDao.delete(favoriteProject);
        return new SuccessResult(MessageResults.projectRemovedFavorite);
    }

    public Result deleteAll(List<FavoriteProject> favoriteProjects) {
        for(FavoriteProject favoriteProject : favoriteProjects){
            delete(favoriteProject);
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }

    public Result deleteById(int id) {
        this.favoriteProjectDao.deleteById(id);
        return new SuccessResult(MessageResults.projectRemovedFavorite);
    }
}
