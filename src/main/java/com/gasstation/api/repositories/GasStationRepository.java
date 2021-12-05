package com.gasstation.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasstation.api.model.entities.GasStation;

public interface GasStationRepository extends JpaRepository<GasStation, Long>{
	
	public Iterable<GasStation> findByNameContainingIgnoreCase(String name);
}
