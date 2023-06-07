package jforce.hrms.service.implementation;

import jforce.hrms.service.ImageService;
import jforce.hrms.service.InformationService;
import jforce.hrms.entities.constants.MessageResults;
import jforce.hrms.core.adapters.abstracts.ImageUploadService;
import jforce.hrms.core.adapters.concretes.CloudinaryServiceAdapter;
import jforce.hrms.core.utilities.results.*;
import jforce.hrms.repository.ImageDao;
import jforce.hrms.entities.Image;
import jforce.hrms.entities.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageManager implements ImageService {
    private final ImageDao imageDao;
    private final InformationService informationService;
    private final String FIELD = "image";

    private final ImageUploadService imageUploadService = new CloudinaryServiceAdapter();


    @Autowired
    public ImageManager(ImageDao imageDao, InformationService informationService){
        this.imageDao = imageDao;
        this.informationService = informationService;
    }

    //Get
    public DataResult<List<Image>> getAll() {
        return new SuccessDataResult<List<Image>>(this.imageDao.findAll(), MessageResults.allDataListed(FIELD));
    }

    public DataResult<List<Image>> getAllByInformationId(int informationId) {
        return new SuccessDataResult<List<Image>>(this.imageDao.getAllByInformation_Id(informationId), MessageResults.allDataListed(FIELD));
    }

    public DataResult<Image> getById(int id) {
        return new SuccessDataResult<Image>(this.imageDao.findById(id).get(), MessageResults.dataListed(FIELD));
    }

    public DataResult<List<Image>> getByIds(List<Integer> ids) {
        List<Image> images = new ArrayList<>();

        for(var id : ids){
            DataResult<Image> imageDataResult = getById(id);
            if(imageDataResult.isSuccess()){
                images.add(imageDataResult.getData());
            }
        }

        return new SuccessDataResult<List<Image>>(images, MessageResults.allDataListed(FIELD));
    }

    //Save
    public Result save(int informationId, MultipartFile file) {
        DataResult<Information> information = informationService.getById(informationId);

        if(!information.isSuccess()){
            return new ErrorResult(MessageResults.notFound(FIELD));
        }

        var uploadedImage = imageUploadService.upload(file);

        if(!uploadedImage.isSuccess()){
            return new ErrorResult(MessageResults.errorWhileUploadingFile);
        }

        Image imageObject = new Image(
                information.getData(),
                uploadedImage.getData().get("url")
        );

        this.imageDao.save(imageObject);
        return new SuccessResult(MessageResults.saved(FIELD));
    }

    public Result delete(Image image) {
        this.imageDao.delete(image);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteById(int id) {
        this.imageDao.deleteById(id);
        return new SuccessResult(MessageResults.deleted(FIELD));
    }

    public Result deleteByIds(List<Integer> ids) {
        for (int id : ids){
            this.imageDao.deleteById(id);
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }

    public Result deleteAll(List<Image> images) {
        for (var image : images){
            this.imageDao.deleteById(image.getId());
        }
        return new SuccessResult(MessageResults.deleteds(FIELD));
    }
}
