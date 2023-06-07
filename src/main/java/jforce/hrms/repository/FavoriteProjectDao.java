package jforce.hrms.repository;

import jforce.hrms.entities.FavoriteProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteProjectDao extends JpaRepository<FavoriteProject, Integer> {
    List<FavoriteProject> getByUser_Id(int id);
    List<FavoriteProject> getByUser_Email(String email);
    List<FavoriteProject> getByProjectAnnounce_Id(int id);
}
