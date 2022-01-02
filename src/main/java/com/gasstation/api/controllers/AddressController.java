package com.gasstation.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasstation.api.model.entities.Address;
import com.gasstation.api.services.AddressService;

@RestController
@RequestMapping(path = "/api/address" )
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PostMapping
	public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
		Address address1 = service.saveAddress(address);
		
		return ResponseEntity.ok().body(address1);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
		Address updatedAddress = service.updateAddress(id, address);
		
		return ResponseEntity.ok().body(updatedAddress);
	}
}
