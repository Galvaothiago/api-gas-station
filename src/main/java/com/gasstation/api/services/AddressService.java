package com.gasstation.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.gasstation.api.model.entities.Address;
import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.repositories.AddressRepository;
import com.gasstation.api.repositories.GasStationRepository;

@Service
public class AddressService {
	@Autowired
	AddressRepository repository;
	
	@Autowired
	GasStationRepository gsRepository;
	
	public Slice<Address> getAllByStreet(String streetName, int page, int quantityItems) {
		if(quantityItems >= 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10; 
		
		Pageable pageResult = PageRequest.of(page, quantityItems);
		return repository.findByStreetContainingIgnoreCase(streetName, pageResult);
		
	}
	
	public Slice<Address> getAllByCity(String cityName, int page, int quantityItems) {
		if(quantityItems >= 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10; 
		
		Pageable pageResult = PageRequest.of(page, quantityItems);
		return repository.findByCityContainingIgnoreCase(cityName, pageResult);
		
	}
	
	public Address saveAddress(Address address) {
		Address address1 = repository.save(address);
		
		Optional<GasStation> gasStation1 = gsRepository.findById(1L);
		gasStation1.get().setAddress(address1);
		return address1;
	}
}