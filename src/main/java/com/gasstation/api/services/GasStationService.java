package com.gasstation.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
