package com.gasstation.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.repositories.GasStationRepository;

@Service
public class GasStationService {
	
	@Autowired
	private GasStationRepository repository;
	
	public List<GasStation> getAll() {
		return repository.findAll();
	}
	
	public Iterable<GasStation> getAllByPage(int page, int quantityItems) {
		if(quantityItems >= 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10; 
		
		Pageable pageResult = PageRequest.of(page, quantityItems);
		return repository.findAll(pageResult);
		
	}
	
	public GasStation findById(Long id) {
		Optional<GasStation> result = repository.findById(id);
		return result.get();
	}
	
	public Iterable<GasStation> getByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}
	
	public GasStation updateInfoGasStation(Long id, GasStation gasStation) {
		GasStation entity = repository.getById(id);
		update(entity, gasStation);
		
		return repository.save(entity);
	}
	
	public void update(GasStation entity, GasStation gasStation) {
		entity.setName(gasStation.getName());
		entity.setUrlImg(gasStation.getUrlImg());
		
	}
	
	public GasStation saveGasStation(GasStation gasStation) {
		return repository.save(gasStation);
	}
	
	public void deleteGasStation(Long id) {
		repository.deleteById(id);
	}
	
	public Iterable<GasStation> findByAddressStreet(String street, int page, int quantityItems) {
		if(quantityItems >= 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10;
		
		String upperStreet = street.toUpperCase();
		
		Pageable result = PageRequest.of(page, quantityItems);
		return repository.findByAddressStreet(upperStreet, result);
		
	}
	
	public Iterable<GasStation> findByAddressCity(String city, int page, int quantityItems) {
		if(quantityItems >= 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10;
		
		String upperCity = city.toUpperCase();
		
		Pageable result = PageRequest.of(page, quantityItems);
		return repository.findByAddressCity(upperCity, result);
		
	}

}
 