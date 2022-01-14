package com.gasstation.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasstation.api.model.auth.User;
import com.gasstation.api.model.entities.GasPrice;
import com.gasstation.api.services.GasPriceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/gasPrice" )
public class GasPriceController {
	
	@Autowired
	private GasPriceService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/{id}")
	public ResponseEntity<GasPrice> saveGasPrice(@PathVariable Long id, @RequestBody GasPrice gasPrice) {
		GasPrice result = service.savePrice(id, gasPrice);
		
		return ResponseEntity.ok().body(result);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<GasPrice> updateGasPrice(@PathVariable Long id, @RequestBody GasPrice gasPrice) {
		GasPrice result = service.updateGasPrice(id, gasPrice);
	
		return ResponseEntity.ok().body(result);
	}	
}
