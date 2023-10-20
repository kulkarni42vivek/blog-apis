package com.blogapi.blogservice.serviceImpl;

import java.io.File;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.blogapi.blogservice.exception.FileProcessingFailedException;

@Service
public class FileService {

	public void saveFile(MultipartFile file, String filePath, Logger log) throws FileProcessingFailedException {
		try {
			if (!file.isEmpty()) {
	            File targetFile = new File(filePath);
	            
	            if (!targetFile.getParentFile().exists()) {
	                targetFile.getParentFile().mkdirs();
	            }
	            file.transferTo(new File(filePath));
	        } else {
	            log.error("Empty file provided for saving.");
	            throw new FileProcessingFailedException("Empty file provided.");
	        }
		}
		catch(Exception e ) {
			e.printStackTrace();
			throw new FileProcessingFailedException("exception occured ");
		}
	}

}
