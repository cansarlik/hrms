package jforce.hrms.api;

import jforce.hrms.service.InformationService;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Information;
import jforce.hrms.entities.dtos.information.InformationSaveDto;
import jforce.hrms.entities.dtos.information.InformationUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/informations")
@CrossOrigin
public class InformationsController {
    private InformationService informationService;

    @Autowired
    public InformationsController(InformationService informationService){
        this.informationService = informationService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<Information>> getAll() {
        return this.informationService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<Information> getById(@PathVariable(value = "id") int id) {
        return this.informationService.getById(id);
    }

    @GetMapping("/getByEmployeeId")
    public DataResult<Information> getByEmployeeId(@RequestParam(value = "employeeId") int employeeId) {
        return this.informationService.getByEmployeeId(employeeId);
    }

    //Post
    @PostMapping("")
    public Result save(@Valid @RequestBody InformationSaveDto information){
        return this.informationService.save(information);
    }

    //Put
    @PutMapping("")
    public Result updateById(@RequestBody InformationUpdateDto information){
        return this.informationService.updateById(information);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody Information information){
        return this.informationService.delete(information);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.informationService.deleteById(id);
    }
}
