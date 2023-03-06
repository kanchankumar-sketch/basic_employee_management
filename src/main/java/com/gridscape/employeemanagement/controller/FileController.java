package com.gridscape.employeemanagement.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gridscape.employeemanagement.dto.ImageUrls;
import com.gridscape.employeemanagement.repository.ImageUrlRepository;
import com.gridscape.employeemanagement.service.FileUploadService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ImageUrlRepository imageUrlRepository;
	
	@PostMapping("/upload")
	public ResponseEntity<ImageUrls> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			ImageUrls url=new ImageUrls();
			url.setUrl(fileUploadService.uploadPicture(file));
			return new ResponseEntity<ImageUrls>(this.imageUrlRepository.save(url),HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public String deleteImage(@PathVariable("id") Long id) {
		Optional<ImageUrls> url=this.imageUrlRepository.findById(id);
		if(url.isPresent()) {
			this.fileUploadService.fileDeleteService(url.get().getUrl());
			this.imageUrlRepository.deleteById(id);
		}
		return "deleted successfully";
	}
}
