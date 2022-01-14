package com.gasstation.api.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasstation.api.model.auth.User;
import com.gasstation.api.model.entities.GasPrice;
import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.repositories.GasStationRepository;
import com.gasstation.api.repositories.PriceRepository;

@Service
public class GasPriceService {
	
	@Autowired
	private PriceRepository repository;
	
	@Autowired
	private GasStationRepository gsRepository;
	
	public GasPrice savePrice(Long gasStation_id, GasPrice gasPrice) {
		GasStation gasStation = gsRepository.getById(gasStation_id);
		
		gasPrice.setId(gasStation.getId());
		gasStation.setPrices(gasPrice);
		
		gsRepository.save(gasStation);
		return gasPrice;
	}
	
	public GasPrice updateGasPrice(Long id, GasPrice gasPrice) {
		GasPrice entity = repository.getById(id);
		update(entity, gasPrice);
		return repository.save(entity);
		
	}
	
	public void update(GasPrice entity, GasPrice gasPrice) {
		entity.setLastEthanolPrice(entity.getEthanol());
		entity.setLastGasPrice(entity.getGasoline());
		entity.setGasoline(gasPrice.getGasoline());
		entity.setEthanol(gasPrice.getEthanol());
		entity.setDiesel(gasPrice.getDiesel());
		entity.setGasolineAdditive(gasPrice.getGasolineAdditive());
		entity.setEthanolAdditive(gasPrice.getEthanolAdditive());
		entity.setupdatedAt(Instant.now());
		entity.setRelationGasEthanol();
		entity.setUpdatedBy(gasPrice.getUpdatedBy());
		
	}
}
 