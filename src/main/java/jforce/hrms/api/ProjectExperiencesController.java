package jforce.hrms.api;

import jforce.hrms.service.ProjectExperienceService;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.ProjectExperience;
import jforce.hrms.entities.dtos.projectExperience.ProjectExperienceSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projectExperiences")
@CrossOrigin
public class ProjectExperiencesController {
    private final ProjectExperienceService projectExperienceService;

    @Autowired
    public ProjectExperiencesController(ProjectExperienceService projectExperienceService){
        this.projectExperienceService = projectExperienceService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<ProjectExperience>> getAll() {
        return this.projectExperienceService.getAll();
    }

    @GetMapping("/getAllByInformationId")
    public DataResult<List<ProjectExperience>> getAllByInformationId(int informationId) {
        return this.projectExperienceService.getAllByInformationId(informationId);
    }

    @GetMapping("/{id}")
    public DataResult<ProjectExperience> getById(@PathVariable(value = "id") int id) {
        return this.projectExperienceService.getById(id);
    }

    //Post
    @PostMapping("")
    public Result save(@RequestBody ProjectExperienceSaveDto projectExperience){
        return this.projectExperienceService.save(projectExperience);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody ProjectExperience projectExperience){
        return this.projectExperienceService.delete(projectExperience);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.projectExperienceService.deleteById(id);
    }
}
