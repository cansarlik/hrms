package jforce.hrms.repository;

import jforce.hrms.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageDao extends JpaRepository<Image, Integer> {
    List<Image> getAllByInformation_Id(int informationId);
}
