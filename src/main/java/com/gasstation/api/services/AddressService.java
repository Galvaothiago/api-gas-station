package com.gasstation.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.gasstation.api.model.CountingCity;
import com.gasstation.api.model.entities.Address;
import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.repositories.AddressRepository;
import com.gasstation.api.repositories.GasStationRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;
	
	@Autowired
	private GasStationRepository gsRepository;
	
	@Autowired
	GasStationService gsService;
	
	public Address saveAddress(Long gasStation_id, Address address) {
		GasStation gasStation = gsRepository.getById(gasStation_id);
		
		address.setId(gasStation.getId());
		gasStation.setAddress(address);
		gsRepository.save(gasStation);
		
		return address;
	}
	
	public Address updateAddress(Long id, Address address) {
		Address entity = repository.getById(id);
		update(entity, address);
		
		entity.getGasStation();
		return repository.save(entity);			
	}
	
	public void update(Address entity, Address address) {
		entity.setStreet(address.getStreet());
		entity.setNumber(address.getNumber());
		entity.setCity(address.getCity());
	}
	
	public CountingCity countingByCity(String city) {
		CountingCity result = repository.findCountCity(city);
		
		return result;
	}
}