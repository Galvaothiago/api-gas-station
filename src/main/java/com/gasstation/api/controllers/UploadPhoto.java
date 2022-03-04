package com.gasstation.api.controllers;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/uploadPhoto")
public class UploadPhoto {

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void updatePhoto(@RequestParam MultipartFile photo) {
		
		String photoName = UUID.randomUUID().toString() + "-" + photo.getOriginalFilename();
		
		Path filePhoto = Path.of("/home/thiagogalvao/gas_station_photos", photoName);
		
		System.out.println(photoName);
		System.out.println(photo.getContentType());
		
		try {
			photo.transferTo(filePhoto);
			System.out.println("photo sent!");
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
