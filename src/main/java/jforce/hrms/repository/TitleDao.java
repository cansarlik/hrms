package jforce.hrms.repository;

import jforce.hrms.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleDao extends JpaRepository<Title, Integer> {
    Title getByTitleName(String titleName);
}
