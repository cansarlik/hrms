package jforce.hrms.core.adapters.abstracts;

import jforce.hrms.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ImageUploadService {
    DataResult<Map<String, String>> upload(MultipartFile file);
    DataResult<Map> delete(String id) throws IOException;
}
