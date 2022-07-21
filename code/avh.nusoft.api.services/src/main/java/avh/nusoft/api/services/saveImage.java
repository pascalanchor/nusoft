package avh.nusoft.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class saveImage {
	@Autowired
	private FileStorageService fileStorageService;
	
	public String saveeImage(MultipartFile photo,String dir) throws Exception {

		String fileName = fileStorageService.storeFile(photo,dir);

        return dir+"/"+fileName;
	}
}
