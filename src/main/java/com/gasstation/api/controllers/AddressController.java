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

import com.gasstation.api.model.entities.Address;
import com.gasstation.api.services.AddressService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/address" )
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/{id}")
	public ResponseEntity<Address> saveAddress(@PathVariable Long id, @RequestBody Address address) {
		Address address1 = service.saveAddress(id, address);
		
		return ResponseEntity.ok().body(address1);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
		Address updatedAddress = service.updateAddress(id, address);
		
		return ResponseEntity.ok().body(updatedAddress);
	}
}
