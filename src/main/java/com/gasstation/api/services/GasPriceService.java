package com.gasstation.api.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasstation.api.model.entities.GasPrice;
import com.gasstation.api.repositories.PriceRepository;

@Service
public class GasPriceService {
	
	@Autowired
	private PriceRepository repository;
	
	public GasPrice findById(Long id) {
		Optional<GasPrice> result = repository.findById(id);
		return result.get();
	}
	
	public GasPrice updateGasPrice(Long id, GasPrice gasPrice) {
		GasPrice entity = repository.getById(id);
		update(entity, gasPrice);
		return repository.save(entity);
		
	}
	
	public void update(GasPrice entity, GasPrice gasPrice) {
		entity.setGasoline(gasPrice.getGasoline());
		entity.setEthanol(gasPrice.getEthanol());
		entity.setDiesel(gasPrice.getDiesel());
		entity.setGasolineAdditive(gasPrice.getGasolineAdditive());
		entity.setEthanolAdditive(gasPrice.getEthanolAdditive());
		entity.setLastUpdate(Instant.now());
		entity.setRelationGasEthanol();
		
	}
	
	public GasPrice createPrice(GasPrice gasPrice) {
		return repository.save(gasPrice);
	}

}
 