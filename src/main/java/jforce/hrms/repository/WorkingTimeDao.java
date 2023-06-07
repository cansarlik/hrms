package jforce.hrms.repository;

import jforce.hrms.entities.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingTimeDao extends JpaRepository<WorkingTime, Integer> {
    WorkingTime getByWorkingTimeName(String workingTimeName);
}
