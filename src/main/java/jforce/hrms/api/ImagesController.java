package jforce.hrms.api;

import jforce.hrms.service.ImageService;
import jforce.hrms.core.utilities.results.DataResult;
import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImagesController {
    private final ImageService imageService;

    @Autowired
    public ImagesController(ImageService imageService){
        this.imageService = imageService;
    }

    //Get
    @GetMapping("")
    public DataResult<List<Image>> getAll() {
        return this.imageService.getAll();
    }

    @GetMapping("/getAllByInformationId")
    public DataResult<List<Image>> getAllByInformationId(int informationId) {
        return this.imageService.getAllByInformationId(informationId);
    }

    @GetMapping("/{id}")
    public DataResult<Image> getById(@PathVariable(value = "id") int id) {
        return this.imageService.getById(id);
    }


    //Post
    @PostMapping("")
    public Result save(@RequestParam int informationId, @RequestBody MultipartFile file){
        return this.imageService.save(informationId, file);
    }


    //Delete
    @DeleteMapping("")
    public Result delete(@RequestBody Image image){
        return this.imageService.delete(image);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") int id){
        return this.imageService.deleteById(id);
    }
}
