package jforce.hrms.repository;

import jforce.hrms.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeDao extends JpaRepository<Office, Integer> {
    Office getByOfficeName(String officeName);
}
