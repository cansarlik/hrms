package jforce.hrms.api;

import jforce.hrms.service.FavoriteProjectService;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.FavoriteProject;
import jforce.hrms.entities.dtos.favoriteProject.FavoriteProjectSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoriteProject")
@CrossOrigin
public class FavoriteProjectsController {
    private final FavoriteProjectService favoriteProjectService;

    @Autowired
    public FavoriteProjectsController(FavoriteProjectService favoriteProjectService){
        this.favoriteProjectService = favoriteProjectService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<FavoriteProject>> getAll() {
        return this.favoriteProjectService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<FavoriteProject> getById(@PathVariable(value = "id") int id) {
        return this.favoriteProjectService.getById(id);
    }

    @GetMapping("/getByProjectAnnounceId")
    public DataResult<List<FavoriteProject>> getByProjectAnnounceId(int id) {
        return this.favoriteProjectService.getByProjectAnnounceId(id);
    }

    @GetMapping("/getByUserId")
    public DataResult<List<FavoriteProject>> getByUserId(int id) {
        return this.favoriteProjectService.getByUserId(id);
    }

    @GetMapping("/getByUserEmail")
    public DataResult<List<FavoriteProject>> getByUserEmail(String email) {
        return this.favoriteProjectService.getByUserEmail(email);
    }

    //Post
    @PostMapping("")
    public DataResult<FavoriteProject> save(@RequestBody FavoriteProjectSaveDto dto){
        return this.favoriteProjectService.save(dto);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody FavoriteProject favoriteProject){
        return this.favoriteProjectService.delete(favoriteProject);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.favoriteProjectService.deleteById(id);
    }
}
