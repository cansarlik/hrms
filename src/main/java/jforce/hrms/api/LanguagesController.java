package jforce.hrms.api;


import jforce.hrms.service.LanguageService;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Language;
import jforce.hrms.entities.dtos.language.LanguageSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@CrossOrigin
public class LanguagesController {
    private final LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService){
        this.languageService = languageService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<Language>> getAll() {
        return this.languageService.getAll();
    }

    @GetMapping("/getAllByInformationId")
    public DataResult<List<Language>> getAllByInformationId(int informationId) {
        return this.languageService.getAllByInformationId(informationId);
    }

    @GetMapping("/{id}")
    public DataResult<Language> getById(@PathVariable(value = "id") int id) {
        return this.languageService.getById(id);
    }

    //Post
    @PostMapping("")
    public Result save(@RequestBody LanguageSaveDto language){
        return this.languageService.save(language);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody Language language){
        return this.languageService.delete(language);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.languageService.deleteById(id);
    }
}