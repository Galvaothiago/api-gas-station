package com.gasstation.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.services.GasStationService;

@RestController
@RequestMapping(path = "/api/gasStation" )
public class GasStationController {
	
	@Autowired
	private GasStationService service;
	
	@GetMapping(path = "/page/{page}/{quantityItems}")
	public ResponseEntity<Iterable<GasStation>> getAllByPageable(@PathVariable int page, @PathVariable int quantityItems) {
		Iterable<GasStation> result = service.getAllByPage(page, quantityItems);
		
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(path = "/search/{searchName}")
	public ResponseEntity<Iterable<GasStation>> getByName(@PathVariable String searchName) {
		Iterable<GasStation> result = service.getByName(searchName);
		
		return ResponseEntity.ok().body(result);
	}
	
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
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<GasStation> updateGasStation(@PathVariable Long id, @RequestBody GasStation gasStation) {
		GasStation result = service.updateInfoGasStation(id, gasStation);
		
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping
	public ResponseEntity<GasStation> createGasStation(@RequestBody GasStation gasStation) {
		GasStation result = service.saveGasStation(gasStation);
		
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteGasStation(@PathVariable Long id) {
		service.deleteGasStation(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
