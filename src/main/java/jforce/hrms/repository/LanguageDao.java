package jforce.hrms.repository;

import jforce.hrms.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageDao extends JpaRepository<Language, Integer> {
    List<Language> getAllByInformation_Id(int informationId);
}
