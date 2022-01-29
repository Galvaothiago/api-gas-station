package com.gasstation.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
		if(quantityItems > 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10;
		
		if(page < 0) page = 0;
		
		Pageable pageResult = PageRequest.of(page, quantityItems);
		return repository.findAll(pageResult);
	}
	
	public GasStation findById(Long id) {
		Optional<GasStation> result = repository.findById(id);
		
		if(result.isPresent()) {
			return result.get();
		}
		
		return null;
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
		try {
			
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Not exist a GasStation with id %d", id));
			
		}
	}
	
	public Iterable<GasStation> findByAddressStreet(String street, int page, int quantityItems) {
		if(quantityItems > 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10;
		
		if(page < 0) page = 0;
		
		String upperStreet = street.toUpperCase();
		
		Pageable result = PageRequest.of(page, quantityItems);
		return repository.findByAddressStreet(upperStreet, result);
		
	}
	
	public Iterable<GasStation> findByAddressCity(String city, int page, int quantityItems) {
		if(quantityItems > 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10;
		
		if(page < 0) page = 0;
		
		String upperCity = city.toUpperCase();
		
		Pageable result = PageRequest.of(page, quantityItems);
		return repository.findByAddressCity(upperCity, result);
		
	}
	
	public Iterable<GasStation> findByPriceOfGasoline(Double price, String city, int page, int quantityItems) {
		if(quantityItems > 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10;
		
		if(page < 0) page = 0;
		if(price <= 0) price = 1.0;
		
		city = city.toUpperCase();
		
		Pageable result = PageRequest.of(page, quantityItems);
		return repository.findByPriceOfGasoline(price, city, result);
		
	}
	
	public Iterable<GasStation> findByPriceOfEthanol(Double price, String city, int page, int quantityItems) {
		if(quantityItems > 20) quantityItems = 20;
		if(quantityItems == 0) quantityItems = 10;
		
		if(page < 0) page = 0;
		if(price <= 0) price = 1.0;
		
		city = city.toUpperCase();
		
		Pageable result = PageRequest.of(page, quantityItems);
		return repository.findByPriceOfEthanol(price, city, result);
		
	}

}
 