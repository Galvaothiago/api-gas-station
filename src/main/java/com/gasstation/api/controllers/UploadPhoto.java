package com.gasstation.api.controllers;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gasstation.api.exceptions.UploadException;
import com.gasstation.api.payload.response.UploadMessage;
import com.gasstation.api.repositories.GasStationRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/uploadPhoto")
public class UploadPhoto {
	
	@Autowired
	GasStationRepository gsRepository;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void updatePhoto(@RequestParam MultipartFile photo, @RequestParam(required = false) Long idGasStation) {
		Set<String> allowedType = new HashSet<>();
		
		allowedType.add("image/png");
		allowedType.add("image/jpeg");
		allowedType.add("image/jpg");
		
		if(!allowedType.contains(photo.getContentType())) {
			throw new UploadException("Type is not supported. Only these types are supported: 'PNG', 'JPEG' and 'JPG'");
		}
		
		String defaultName = photo.getOriginalFilename();
		
		String gasStationName = gsRepository.findGasStationNameById(idGasStation);
		
		if(idGasStation != null && gasStationName != null) {
			defaultName = gasStationName;
		}
		
		String photoName = defaultName + "-" + UUID.randomUUID().toString();
		Path filePhoto = Path.of("/home/thiagogalvao/gas_station_photos", photoName);
		
		try {
			photo.transferTo(filePhoto);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@ExceptionHandler(UploadException.class)
	public ResponseEntity<?> UploadException(UploadException e) {
		UploadMessage errorMessage = new UploadMessage();
		
		errorMessage.setMessageError(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorMessage);
	}
}
