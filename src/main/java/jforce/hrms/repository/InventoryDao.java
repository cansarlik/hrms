package jforce.hrms.repository;

import jforce.hrms.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryDao extends JpaRepository<Inventory, Integer> {
    List<Inventory> getAllByInformation_Id(int informationId);
}
