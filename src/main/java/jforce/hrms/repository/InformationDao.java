package jforce.hrms.repository;

import jforce.hrms.entities.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface InformationDao extends JpaRepository<Information, Integer> {
    Information getByEmployee_UserId(int employeeId);

    @Transactional
    @Modifying
    @Query("update Information r set r.description=:description, r.githubUrl=:githubUrl, r.linkedinUrl=:linkedinUrl where r.id=:id")
    void updateById(int id, String description, String githubUrl, String linkedinUrl);
}
