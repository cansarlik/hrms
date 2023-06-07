package jforce.hrms.service;

import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Inventory;
import jforce.hrms.entities.dtos.inventory.InventorySaveDto;

import java.util.List;

public interface InventoryService {
    //Get
    DataResult<List<Inventory>> getAll();
    DataResult<List<Inventory>> getAllByInformationId(int informationId);
    DataResult<Inventory> getById(int id);
    DataResult<List<Inventory>> getByIds(List<Integer> ids);

    //Post
    Result save(InventorySaveDto inventory);

    //Delete
    Result delete(Inventory inventory);
    Result deleteById(int id);
    Result deleteByIds(List<Integer> ids);
    Result deleteAll(List<Inventory> inventories);
}
