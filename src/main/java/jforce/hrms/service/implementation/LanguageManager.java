package jforce.hrms.service.implementation;

import jforce.hrms.service.LanguageService;
import jforce.hrms.service.InformationService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.core.utilities.tools.StringTools;
import jforce.hrms.repository.LanguageDao;
import jforce.hrms.entities.Language;
import jforce.hrms.entities.Information;
import jforce.hrms.entities.dtos.language.LanguageSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageManager implements LanguageService {
    private final LanguageDao languageDao;
    private final InformationService informationService;
    private final String FIELD = "language";

    @Autowired
    private LanguageManager(LanguageDao languageDao, InformationService informationService){
        this.languageDao = languageDao;
        this.informationService = informationService;
    }


    public DataResult<List<Language>> getAll() {
        return new SuccessDataResult<List<Language>>(this.languageDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<Language>> getAllByInformationId(int informationId) {
        return new SuccessDataResult<List<Language>>(this.languageDao.getAllByInformation_Id(informationId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Language> getById(int id) {
        return new SuccessDataResult<Language>(this.languageDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<List<Language>> getByIds(List<Integer> ids) {
        List<Language> languages = new ArrayList<>();

        for(var id : ids){
            DataResult<Language> languageDataResult = getById(id);
            if(languageDataResult.isSuccess()){
                languages.add(languageDataResult.getData());
            }
        }

        return new SuccessDataResult<List<Language>>(languages, MessageResults.allDataListed(FIELD));
    }

    public Result save(LanguageSaveDto language) {
        if(StringTools.isStringNullOrEmpty(language.getLanguageName()) ||
                StringTools.isStringNullOrEmpty(String.valueOf(language.getLanguageLevel()))){
            return new ErrorResult(MessageResults.emptyFields);
        }

        DataResult<Information> information = informationService.getById(language.getInformationId());

        if(!information.isSuccess()){
            return new ErrorResult(MessageResults.notFound(FIELD));
        }

        Language languageObject = new Language(
                information.getData(),
                language.getLanguageName(),
                language.getLanguageLevel()
        );

        this.languageDao.save(languageObject);
        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result delete(Language language) {
        this.languageDao.delete(language);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        this.languageDao.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteByIds(List<Integer> ids) {
        for (int id : ids){
            this.languageDao.deleteById(id);
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }

    public Result deleteAll(List<Language> languages) {
        for (var language : languages){
            this.languageDao.deleteById(language.getId());
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }
}
