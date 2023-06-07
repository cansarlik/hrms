package jforce.hrms.service.implementation;

import jforce.hrms.service.InformationService;
import jforce.hrms.service.InventoryService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.core.utilities.tools.StringTools;
import jforce.hrms.repository.InventoryDao;
import jforce.hrms.entities.Information;
import jforce.hrms.entities.Inventory;
import jforce.hrms.entities.dtos.inventory.InventorySaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryManager implements InventoryService {

    private final InventoryDao inventoryDao;
    private final InformationService informationService;
    private final String FIELD = "inventory";

    @Autowired
    public InventoryManager(InventoryDao inventoryDao, InformationService informationService){
        this.inventoryDao = inventoryDao;
        this.informationService = informationService;
    }

    public DataResult<List<Inventory>> getAll() {
        return new SuccessDataResult<List<Inventory>>(this.inventoryDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<Inventory>> getAllByInformationId(int informationId) {
        return new SuccessDataResult<List<Inventory>>(this.inventoryDao.getAllByInformation_Id(informationId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Inventory> getById(int id) {
        return new SuccessDataResult<Inventory>(this.inventoryDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<List<Inventory>> getByIds(List<Integer> ids) {
        List<Inventory> inventories = new ArrayList<>();

        for(var id : ids){
            DataResult<Inventory> inventoryDataResult = getById(id);
            if(inventoryDataResult.isSuccess()){
                inventories.add(inventoryDataResult.getData());
            }
        }

        return new SuccessDataResult<List<Inventory>>(inventories, MessageResults.allDataListed(FIELD));
    }

    public Result save(InventorySaveDto inventory) {
        if(StringTools.isStringNullOrEmpty(inventory.getItemName()) ||
                StringTools.isStringNullOrEmpty(inventory.getDelivererStaff()) ||
                StringTools.isStringNullOrEmpty(String.valueOf(inventory.getStartYear()))){
            return new ErrorResult(MessageResults.emptyFields);
        }
        DataResult<Information> information = informationService.getById(inventory.getInformationId());

        if(!information.isSuccess()){
            return new ErrorResult(MessageResults.notFound(FIELD));
        }

        Inventory inventoryObject = new Inventory(
                information.getData(),
                inventory.getItemName(),
                inventory.getDelivererStaff(),
                inventory.getStartYear(),
                inventory.getEndYear()
        );
        this.inventoryDao.save(inventoryObject);
        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result delete(Inventory inventory) {
        this.inventoryDao.delete(inventory);
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }

    public Result deleteById(int id) {
        this.inventoryDao.deleteById(id);
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }

    public Result deleteByIds(List<Integer> ids) {
        for (int id : ids){
            this.inventoryDao.deleteById(id);
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }

    public Result deleteAll(List<Inventory> inventories) {
        for (var inventory : inventories){
            this.inventoryDao.deleteById(inventory.getId());
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }
}
