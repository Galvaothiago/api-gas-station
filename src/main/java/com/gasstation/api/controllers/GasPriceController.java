package com.gasstation.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasstation.api.model.entities.GasPrice;
import com.gasstation.api.services.GasPriceService;

@RestController
@RequestMapping(path = "/api/gasPrice" )
public class GasPriceController {
	
	@Autowired
	private GasPriceService service;
	
	@PostMapping(path = "/{id}")
	public ResponseEntity<GasPrice> saveGasPrice(@PathVariable Long id, @RequestBody GasPrice gasPrice) {
		GasPrice result = service.savePrice(id, gasPrice);
		
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<GasPrice> updateGasPrice(@PathVariable Long id, @RequestBody GasPrice gasPrice) {
		GasPrice result = service.updateGasPrice(id, gasPrice);
	
		return ResponseEntity.ok().body(result);
	}	
}
