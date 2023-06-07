package jforce.hrms.service;


import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    //Get
    DataResult<List<Image>> getAll();
    DataResult<List<Image>> getAllByInformationId(int informationId);
    DataResult<Image> getById(int id);
    DataResult<List<Image>> getByIds(List<Integer> ids);

    //Post
    Result save(int informationId, MultipartFile file);

    //Delete
    Result delete(Image image);
    Result deleteById(int id);
    Result deleteByIds(List<Integer> ids);
    Result deleteAll(List<Image> images);
}
