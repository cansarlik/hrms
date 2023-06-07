package jforce.hrms.repository;

import jforce.hrms.entities.Company;
import jforce.hrms.entities.dtos.company.CompanyUpdateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyDao extends JpaRepository<Company, Integer> {
    Company getByUser_Email(String email);

    @Transactional
    @Modifying
    @Query("update Company e set e.companyName=(:#{#dto.companyName}), e.website=(:#{#dto.website}), e.phone=(:#{#dto.phone}) where e.userId=(:#{#dto.userId})")
    void updateById(CompanyUpdateDto dto);
}
