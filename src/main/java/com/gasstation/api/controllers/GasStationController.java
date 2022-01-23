package com.gasstation.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.services.GasStationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = {"/api/gasStation"})
public class GasStationController {
	
	@Autowired
	private GasStationService service;
	
	@GetMapping(path = "/searchStreet/{street}/{page}/{quantityItems}")
	public ResponseEntity<Iterable<GasStation>> getAllByAddressStreet(
			@PathVariable String street, 
			@PathVariable int page, 
			@PathVariable int quantityItems) {
		Iterable<GasStation> result = service.findByAddressStreet(street, page, quantityItems);
		
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(path = "/searchCity/{city}/{page}/{quantityItems}")
	public ResponseEntity<Iterable<GasStation>> getAllByAddressCity(
			@PathVariable String city, 
			@PathVariable int page, 
			@PathVariable int quantityItems) {
		Iterable<GasStation> result = service.findByAddressCity(city, page, quantityItems);
		
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(path = "/searchPrice/gasoline/{city}/{price}/{page}/{quantityItems}")
	public ResponseEntity<Iterable<GasStation>> getAllByPriceOfGasoline(
			@PathVariable String city,
			@PathVariable Double price, 
			@PathVariable int page, 
			@PathVariable int quantityItems) {
		Iterable<GasStation> result = service.findByPriceOfGasoline(price, city, page, quantityItems);
		
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(path = "/searchPrice/ethanol/{city}/{price}/{page}/{quantityItems}")
	public ResponseEntity<Iterable<GasStation>> getAllByPriceOfEthanol(
			@PathVariable String city,
			@PathVariable Double price, 
			@PathVariable int page, 
			@PathVariable int quantityItems) {
		Iterable<GasStation> result = service.findByPriceOfEthanol(price, city, page, quantityItems);
		
		return ResponseEntity.ok().body(result);
	}
	
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
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<GasStation>> getAll() {
		List<GasStation> list = service.getAll();

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GasStation> getById(@PathVariable Long id) {
		GasStation result = service.findById(id);
		
		if(result != null) {
			return ResponseEntity.ok().body(result);	
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<GasStation> updateGasStation(@PathVariable Long id, @RequestBody GasStation gasStation) {
		GasStation result = service.updateInfoGasStation(id, gasStation);
		
		return ResponseEntity.ok().body(result);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<GasStation> createGasStation(@RequestBody GasStation gasStation) {
		GasStation result = service.saveGasStation(gasStation);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteGasStation(@PathVariable Long id) {
		service.deleteGasStation(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
