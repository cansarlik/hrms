package jforce.hrms.repository;

import jforce.hrms.entities.ProjectAnnounce;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceFilterDto;
import jforce.hrms.entities.dtos.projectAnnounce.ProjectAnnounceUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectAnnounceDao extends JpaRepository<ProjectAnnounce, Integer> {
    List<ProjectAnnounce> getAllByOrderByReleaseDateDesc();
    List<ProjectAnnounce> getAllByCompany_UserId(int companyId);
    List<ProjectAnnounce> getAllByCompany_UserIdOrderByReleaseDateAsc(int companyId);
    List<ProjectAnnounce> getAllByCompany_UserIdOrderByReleaseDateDesc(int companyId);

    List<ProjectAnnounce> getByActiveTrue();
    List<ProjectAnnounce> getByConfirmedTrue();
    List<ProjectAnnounce> getByActiveTrueAndConfirmedTrue();
    List<ProjectAnnounce> getByActiveTrueOrderByReleaseDateDesc();
    List<ProjectAnnounce> getByActiveTrueAndConfirmedTrueOrderByReleaseDateAsc();
    List<ProjectAnnounce> getByActiveTrueAndConfirmedTrueOrderByReleaseDateDesc();


    List<ProjectAnnounce> getByActiveTrueAndCompany_UserId(int companyId);
    List<ProjectAnnounce> getByActiveTrueAndCompany_UserIdOrderByReleaseDateDesc(int companyId);

    @Query("Select j from jforce.hrms.entities.ProjectAnnounce j where " +
            "((:#{#filter.officeIds}) IS NULL OR j.office.id IN (:#{#filter.officeIds})) and " +
            "((:#{#filter.titleIds}) IS NULL OR j.title.id IN (:#{#filter.titleIds})) and " +
            "((:#{#filter.workingTimeIds}) IS NULL OR j.workingTime.id IN (:#{#filter.workingTimeIds})) and " +
            "j.active=true and j.confirmed=true and " +
            "((:#{#filter.search}) IS NULL OR j.description like %:#{#filter.search}% OR LOWER(j.description) like %:#{#filter.search}% OR UPPER(j.description) like %:#{#filter.search}%) and " +
            "((:#{#filter.minSalary}) IS NULL OR (:#{#filter.minSalary}) = 0 OR j.minSalary >= (:#{#filter.minSalary})) and " +
            "((:#{#filter.maxSalary}) IS NULL OR (:#{#filter.maxSalary}) = 0 OR j.maxSalary <= (:#{#filter.maxSalary}))" +
            " ORDER BY j.releaseDate DESC")
    public Page<ProjectAnnounce> getByFilter(@Param("filter") ProjectAnnounceFilterDto filter, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update ProjectAnnounce j set j.active=:active where j.id=:id")
    void updateActive(boolean active, int id);

    @Transactional
    @Modifying
    @Query("update ProjectAnnounce j set j.confirmed=:confirmed where j.id=:id")
    void updateConfirmed(boolean confirmed, int id);

    @Transactional
    @Modifying
    @Query("update ProjectAnnounce j set j.active=(:#{#project.active}), j.description=(:#{#project.description}), j.office.id=(:#{#project.officeIds}), " +
            "j.workingTime.id=(:#{#project.workingTimeId}), j.title.id=(:#{#project.titleIds}), j.deadline=(:#{#project.deadline}), " +
            "j.minSalary=(:#{#project.minSalary}), j.maxSalary=(:#{#project.maxSalary}), j.openTitlesAmount=(:#{#project.openTitlesAmount}) " +
            "where j.id=(:#{#project.id})")
    void updateById(ProjectAnnounceUpdateDto project);
}
