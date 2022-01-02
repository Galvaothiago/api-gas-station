package com.gasstation.api.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gasstation.api.model.entities.GasStation;

public interface GasStationRepository extends JpaRepository<GasStation, Long>{
	
	public Iterable<GasStation> findByNameContainingIgnoreCase(String name);
	
	@Query("select g from GasStation g join g.address address where UPPER(address.street) like %?1%")
	public Slice<GasStation> findByAddressStreet(String street, Pageable pageable);
	
	@Query("select g from GasStation g join g.address address where UPPER(address.city) like %?1%")
	public Slice<GasStation> findByAddressCity(String city, Pageable pageable);
	
	@Query(
		"select g from GasStation g join g.prices prices join g.address address where UPPER(address.city) = ?2 and prices.gasoline <= ?1")
	public Slice<GasStation> findByPriceOfGasoline(Double price, String city, Pageable pageable);
}
