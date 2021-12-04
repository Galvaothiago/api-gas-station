package com.gasstation.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.services.GasStationService;

@RestController
@RequestMapping(path = "/api/gasStation" )
public class GasStationController {
	
	@Autowired
	private GasStationService service;
	
	@GetMapping
	public ResponseEntity<List<GasStation>> getAll() {
		List<GasStation> list = service.getAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GasStation> getById(@PathVariable Long id) {
		GasStation result = service.findById(id);
		
		return ResponseEntity.ok().body(result);
	}
	
}
