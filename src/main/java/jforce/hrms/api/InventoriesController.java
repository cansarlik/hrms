package jforce.hrms.api;

import jforce.hrms.service.InventoryService;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Inventory;
import jforce.hrms.entities.dtos.inventory.InventorySaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@CrossOrigin
public class InventoriesController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoriesController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<Inventory>> getAll() {
        return this.inventoryService.getAll();
    }

    @GetMapping("/getAllByInformationId")
    public DataResult<List<Inventory>> getAllByInformationId(int informationId) {
        return this.inventoryService.getAllByInformationId(informationId);
    }

    @GetMapping("/{id}")
    public DataResult<Inventory> getById(@PathVariable(value = "id") int id) {
        return this.inventoryService.getById(id);
    }

    //Post
    @PostMapping("")
    public Result save(@RequestBody InventorySaveDto inventory){
        return this.inventoryService.save(inventory);
    }

    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody Inventory inventory){
        return this.inventoryService.delete(inventory);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.inventoryService.deleteById(id);
    }
}
