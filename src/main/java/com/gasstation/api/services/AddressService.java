package com.gasstation.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.gasstation.api.model.entities.Address;
import com.gasstation.api.repositories.AddressRepository;

@Service
public class AddressService {
	@Autowired
	AddressRepository repository;
	
	public Slice<Address> getAllByStreet(String streetName, int page, int quantityItems) {
		if(quantityItems >= 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10; 
		
		Pageable pageResult = PageRequest.of(page, quantityItems);
		return repository.findByStreetContainingIgnoreCase(streetName, pageResult);
		
	}
	
//	public Iterable<Address> getAllByCity(String cityName, int page, int quantityItems) {
//		if(quantityItems >= 20) quantityItems = 20;
//		if(quantityItems == 0) quantityItems = 10; 
//		
//		Pageable pageResult = PageRequest.of(page, quantityItems);
//		return repository.findByCityIgnoreCase(cityName, pageResult);
//		
//	}
}
