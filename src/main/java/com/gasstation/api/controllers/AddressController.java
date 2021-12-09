package com.gasstation.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasstation.api.model.entities.Address;
import com.gasstation.api.model.entities.GasPrice;
import com.gasstation.api.services.AddressService;

@RestController
@RequestMapping(path = "/api/search" )
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@GetMapping(value = "/searchByStreet/{streetName}/{page}/{quantity}")
	public ResponseEntity<Iterable<Address>> getAllByStreet(@PathVariable String streetName, @PathVariable int page, @PathVariable int quantity) {
		Slice<Address> result = service.getAllByStreet(streetName, page, quantity);
		
		return ResponseEntity.ok().body(result);
	}
	
//	@PutMapping(value = "/searchByCity/{cityName}/{page}/{quantity}")
//	public ResponseEntity<Iterable<Address>> getAllByCity(@PathVariable String cityName, @PathVariable int page, @PathVariable int quantity) {
//	Iterable<Address> result = service.getAllByCity(cityName, page, quantity);
//		
//		return ResponseEntity.ok().body(result);
//	}
	
}
