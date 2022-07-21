package avh.nusoft.api.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import avh.nusoft.api.persistence.NusoftRep;

@Service
public class FileStorageService {
	//@Autowired private NusoftRep rep;
	@Autowired
    public FileStorageService(){

    }
    public String storeFile(MultipartFile file,String path) throws Exception {
        // Normalize file name
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    	String fileName = UUID.randomUUID().toString() + "." +file.getOriginalFilename().split("\\.")[1];
        Path fileStorageLocation = Paths.get(path)
                .toAbsolutePath().normalize();
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
            
        } catch (IOException ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    	
    }
}
