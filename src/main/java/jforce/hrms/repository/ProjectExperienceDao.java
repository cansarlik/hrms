package jforce.hrms.repository;

import jforce.hrms.entities.ProjectExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectExperienceDao extends JpaRepository<ProjectExperience, Integer> {
    List<ProjectExperience> getAllByInformation_Id(int informationId);
}
