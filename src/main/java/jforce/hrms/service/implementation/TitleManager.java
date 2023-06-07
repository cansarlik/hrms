package jforce.hrms.service.implementation;

import jforce.hrms.service.TitleService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.repository.TitleDao;
import jforce.hrms.entities.Title;
import jforce.hrms.entities.dtos.title.TitleSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TitleManager implements TitleService {
    private final TitleDao titleDao;
    private final String FIELD = "title";

    @Autowired
    public TitleManager(TitleDao titleDao){
        this.titleDao = titleDao;
    }

    //Get
    public DataResult<List<Title>>  getAll() {
        return new SuccessDataResult<List<Title>>(titleDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Title> getById(int id) {
        return new SuccessDataResult<Title>(titleDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<Title> getByTitleName(String titleName) {
        return new SuccessDataResult<Title>(titleDao.getByTitleName(titleName), MessageResults.dataListed(FIELD));
    }

    //Save
    public Result save(TitleSaveDto title) {
        if(getByTitleName(title.getTitleName()).getData() != null){
            return new ErrorResult(MessageResults.alreadyExists(FIELD));
        }

        Title titleObject = new Title(title.getTitleName());
        this.titleDao.save(titleObject);
        return new SuccessResult(MessageResults.saved(FIELD));
    }

    //Delete
    public Result delete(Title title){
        this.titleDao.delete(title);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id){
        this.titleDao.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }
}
