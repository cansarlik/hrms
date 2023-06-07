package jforce.hrms.api;

import jforce.hrms.service.OfficeService;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Office;

import jforce.hrms.entities.dtos.office.OfficeSaveDto;
import jforce.hrms.entities.dtos.office.OfficeSaveMultipleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/offices")
@CrossOrigin
public class OfficesController {
    private final OfficeService officeService;

    @Autowired
    public OfficesController(OfficeService officeService){
        super();
        this.officeService = officeService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<Office>> getAll()  {
        return this.officeService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<Office> getById(@PathVariable(value = "id") int id){
        return this.officeService.getById(id);
    }

    @GetMapping("/getByOfficeName")
    public DataResult<Office> getByOfficeName(@RequestParam(value = "officeName") String officeName){
        return this.officeService.getByOfficeName(officeName);
    }
    //Post
    @PostMapping("")
    public Result save(@Valid @RequestBody OfficeSaveDto office){
        return this.officeService.save(office);
    }

    @PostMapping("/saveMultiple")
    public Result saveMultiple(@Valid @RequestBody OfficeSaveMultipleDto dto){
        return this.officeService.saveMultiple(dto);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody Office office){
        return this.officeService.delete(office);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.officeService.deleteById(id);
    }
}
