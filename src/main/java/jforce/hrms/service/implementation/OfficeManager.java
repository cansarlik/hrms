package jforce.hrms.service.implementation;

import jforce.hrms.service.OfficeService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.repository.OfficeDao;
import jforce.hrms.entities.Office;
import jforce.hrms.entities.dtos.office.OfficeSaveDto;
import jforce.hrms.entities.dtos.office.OfficeSaveMultipleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeManager implements OfficeService {
    private final OfficeDao officeDao;
    private final String FIELD = "office";

    @Autowired
    public OfficeManager(OfficeDao officeDao){
        this.officeDao = officeDao;
    }

    public DataResult<List<Office>> getAll() {
        return new SuccessDataResult<List<Office>>(this.officeDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Office> getById(int id) {
        return new SuccessDataResult<Office>(this.officeDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<Office> getByOfficeName(String officeName) {
        return new SuccessDataResult<Office>(this.officeDao.getByOfficeName(officeName), MessageResults.dataListed(FIELD));
    }

    public Result save(OfficeSaveDto office) {
        if(getByOfficeName(office.getOfficeName()).getData() != null){
            return new ErrorResult(MessageResults.alreadyExists(FIELD));
        }

        Office officeObject = new Office(office.getOfficeName());
        this.officeDao.save(officeObject);
        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result saveMultiple(OfficeSaveMultipleDto dto) {
        for(var office : dto.getOfficeNames()){
            OfficeSaveDto saveDto = new OfficeSaveDto();
            saveDto.setOfficeName(office);
            save(saveDto);
        }
        return new SuccessResult(MessageResults.saveds(FIELD));
    }

    public Result delete(Office office) {
        this.officeDao.delete(office);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        this.officeDao.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }
}
