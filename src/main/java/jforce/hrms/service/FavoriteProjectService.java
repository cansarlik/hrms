package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.FavoriteProject;
import jforce.hrms.entities.dtos.favoriteProject.FavoriteProjectSaveDto;

import java.util.List;

public interface FavoriteProjectService {
    //Get
    DataResult<List<FavoriteProject>> getAll();
    DataResult<FavoriteProject> getById(int id);

    DataResult<List<FavoriteProject>> getByProjectAnnounceId(int id);
    DataResult<List<FavoriteProject>> getByUserId(int id);
    DataResult<List<FavoriteProject>> getByUserEmail(String email);

    //Post
    DataResult<FavoriteProject> save(FavoriteProjectSaveDto dto);

    //Delete
    Result delete(FavoriteProject favoriteProject);
    Result deleteAll(List<FavoriteProject> favoriteProjects);
    Result deleteById(int id);
}
