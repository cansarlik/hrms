package jforce.hrms.repository;

import jforce.hrms.entities.Staff;
import jforce.hrms.entities.dtos.staff.StaffUpdateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StaffDao extends JpaRepository<Staff, Integer> {
    Staff getByUser_Email(String email);

    @Transactional
    @Modifying
    @Query("update Staff p set p.firstName=(:#{#dto.firstName}), p.lastName=(:#{#dto.lastName}) where p.userId=(:#{#dto.userId})")
    void updateById(StaffUpdateDto dto);
}
