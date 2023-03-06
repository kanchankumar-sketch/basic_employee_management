package com.gridscape.employeemanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private final Path root = Paths.get("upload/files");
	
	public String uploadPicture(MultipartFile file) throws IOException {
		try {
			if (!Files.exists(this.root)) {
				new File(this.root.toString()).mkdir();
			}
			int i = file.getOriginalFilename().lastIndexOf('.');
			String extension = "";
			if (i > 0) {
				extension = file.getOriginalFilename().substring(i + 1);
			}
			String fileName = "PROFILE_" + (UUID.randomUUID().toString()) + "." + (extension);
			Files.copy(file.getInputStream(), this.root.resolve(fileName));
			return "files/"+fileName;

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void fileDeleteService(String url) {
		try {
			Files.delete(Paths.get("upload/").resolve(url));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}
}
