package jforce.hrms.api;

import jforce.hrms.service.TitleService;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Title;

import jforce.hrms.entities.dtos.title.TitleSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
@CrossOrigin
public class TitlesController {
    private final TitleService titleService;

    @Autowired
    public TitlesController(TitleService titleService){
        super();
        this.titleService = titleService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<Title>> getAll()  {
        return this.titleService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<Title> getById(@PathVariable(value = "id") int id){
        return this.titleService.getById(id);
    }

    @GetMapping("/getByTitleName")
    public DataResult<Title> getByTitleName(@RequestParam(value = "titleName") String titleName){
        return this.titleService.getByTitleName(titleName);
    }

    //Save
    @PostMapping("")
    public Result save(@RequestBody TitleSaveDto title){
        return this.titleService.save(title);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody Title title){
        return this.titleService.delete(title);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.titleService.deleteById(id);
    }


}
