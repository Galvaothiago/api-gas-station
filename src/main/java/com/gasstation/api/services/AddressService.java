package com.gasstation.api.services;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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
	
	public Address saveAddress(Long gasStation_id, Address address) {
		GasStation gasStation = gsRepository.getById(gasStation_id);
		
		address.setId(gasStation.getId());
		gasStation.setAddress(address);
		gsRepository.save(gasStation);
		
		return address;
	}
	
	public Address partialUpdate(Long id, Map<String, String> fields) {
		Optional<Address> entity = repository.findById(id);
		
		if(entity.isPresent()) {
			fillProperty(fields, entity.get());
			return repository.save(entity.get());
		}
		return null;
	}
	
	public void fillProperty(Map<String, String> fields, Address address) {
		fields.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(Address.class, propertyName);
			field.setAccessible(true);
			
			System.out.println(propertyName + " = " + propertyValue);
			
			ReflectionUtils.setField(field, address, propertyValue);
			System.out.println(field.toString());
		});
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
	
	public Iterable<CountingCity> countAllCity() {
		Iterable<CountingCity> result = repository.countAllCity();
		
		return result;
	}
}