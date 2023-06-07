package jforce.hrms.api;

import jforce.hrms.service.ProjectAnnounceService;
import jforce.hrms.core.utilities.results.DataResult;

import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.ProjectAnnounce;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceFilterDto;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceSaveDto;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projectAnnounces")
@CrossOrigin
public class ProjectAnnouncesController {
    private ProjectAnnounceService projectAnnounceService;

    @Autowired
    public ProjectAnnouncesController(ProjectAnnounceService officeService){
        super();
        this.projectAnnounceService = officeService;
    }

    //Post
    @PostMapping("")
    public Result save(@RequestBody ProjectAnnounceSaveDto projectAnnounce){
        return this.projectAnnounceService.save(projectAnnounce);
    }

    //Put
    @PutMapping("")
    public Result updateById(@RequestBody ProjectAnnounceUpdateDto projectAnnounce){
        return this.projectAnnounceService.updateById(projectAnnounce);
    }

    @PutMapping("/updateActive")
    public Result updateActive(@RequestParam(value = "active") boolean active, @RequestParam(value = "id") int id){
        return this.projectAnnounceService.updateActive(active,id);
    }

    @PutMapping("/updateConfirmed")
    public Result updateConfirmed(@RequestParam(value = "confirmed") boolean confirmed, @RequestParam(value = "id") int id){
        return this.projectAnnounceService.updateConfirmed(confirmed,id);
    }

    //Get
    @GetMapping("")
    public DataResult<List<ProjectAnnounce>> getAll()  {
        return this.projectAnnounceService.getAll();
    }

    @GetMapping("/getAllByPage")
    public DataResult<List<ProjectAnnounce>> getAll(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize)  {
        return this.projectAnnounceService.getAll(pageNo, pageSize);
    }

    @GetMapping("/getAllOrderByReleaseDateDesc")
    public DataResult<List<ProjectAnnounce>> getAllOrderByReleaseDateDesc(){
        return this.projectAnnounceService.getAllOrderByReleaseDateDesc();
    }

    @GetMapping("/getAllByCompanyId")
    public DataResult<List<ProjectAnnounce>> getAllByCompanyId(int companyId)  {
        return this.projectAnnounceService.getAllByCompanyId(companyId);
    }

    @GetMapping("/getAllByCompanyIdOrderByReleaseDateAsc")
    public DataResult<List<ProjectAnnounce>> getAllByCompanyIdOrderByReleaseDateAsc(int companyId)  {
        return this.projectAnnounceService.getAllByCompanyIdOrderByReleaseDateAsc(companyId);
    }

    @GetMapping("/getAllByCompanyIdOrderByReleaseDateDesc")
    public DataResult<List<ProjectAnnounce>> getAllByCompanyIdOrderByReleaseDateDesc(int companyId)  {
        return this.projectAnnounceService.getAllByCompanyIdOrderByReleaseDateDesc(companyId);
    }



    @GetMapping("/{id}")
    public DataResult<ProjectAnnounce> getById(@PathVariable(value = "id") int id){
        return this.projectAnnounceService.getById(id);
    }

    @GetMapping("/getByActiveTrue")
    public DataResult<List<ProjectAnnounce>> getByActiveTrue(){
        return this.projectAnnounceService.getByActiveTrue();
    }

    @GetMapping("/getByConfirmedTrue")
    public DataResult<List<ProjectAnnounce>> getByConfirmedTrue(){
        return this.projectAnnounceService.getByConfirmedTrue();
    }

    @GetMapping("/getByActiveTrueAndConfirmedTrue")
    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrue(){
        return this.projectAnnounceService.getByActiveTrueAndConfirmedTrue();
    }

    @GetMapping("/getByActiveTrueOrderByReleaseDate")
    public DataResult<List<ProjectAnnounce>> getByActiveTrueOrderByReleaseDate(){
        return this.projectAnnounceService.getByActiveTrueOrderByReleaseDate();
    }

    @GetMapping("/getByActiveTrueAndConfirmedTrueOrderByReleaseDateAsc")
    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrueOrderByReleaseDateAsc(){
        return this.projectAnnounceService.getByActiveTrueAndConfirmedTrueOrderByReleaseDateAsc();
    }

    @GetMapping("/getByActiveTrueAndConfirmedTrueOrderByReleaseDateDesc")
    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndConfirmedTrueOrderByReleaseDateDesc(){
        return this.projectAnnounceService.getByActiveTrueAndConfirmedTrueOrderByReleaseDateDesc();
    }


    @GetMapping("/getByActiveTrueAndCompanyIdOrderByReleaseDate")
    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndCompanyIdOrderByReleaseDate(int companyId){
        return this.projectAnnounceService.getByActiveTrueAndCompanyIdOrderByReleaseDate(companyId);
    }

    @GetMapping("/getByActiveTrueAndCompanyId")
    public DataResult<List<ProjectAnnounce>> getByActiveTrueAndCompanyId(@RequestParam(value = "companyId") int companyId){
        return this.projectAnnounceService.getByActiveTrueAndCompanyId(companyId);
    }

    @PostMapping("/getByFilter")
    public DataResult<List<ProjectAnnounce>> getByFilter(@RequestBody ProjectAnnounceFilterDto filter, int pageNo, int pageSize){
        return this.projectAnnounceService.getByFilter(filter, pageNo, pageSize);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody ProjectAnnounce projectAnnounce){
        return this.projectAnnounceService.delete(projectAnnounce);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.projectAnnounceService.deleteById(id);
    }

}
