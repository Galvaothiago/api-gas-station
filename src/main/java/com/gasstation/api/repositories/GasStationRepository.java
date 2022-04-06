package com.gasstation.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gasstation.api.model.entities.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Long> {
	
	public Iterable<GasStation> findByNameContainingIgnoreCase(String name);
	
	@Query("select g.name from GasStation g where g.id = ?1")
	public String findGasStationNameById(Long id);
	
	@Query("select g from GasStation g join fetch g.prices join fetch g.address address where UPPER(address.street) like %?1%")
	public Slice<GasStation> findByAddressStreet(String street, Pageable pageable);
	
	@Query("select g from GasStation g join fetch g.prices join fetch g.address address where UPPER(address.city) like %?1%")
	public Slice<GasStation> findByAddressCity(String city, Pageable pageable);
	
	@Query("select g from GasStation g join fetch g.prices prices join fetch g.address address where UPPER(address.city) = ?2 and prices.gasoline <= ?1")
	public Slice<GasStation> findByPriceOfGasoline(Double price, String city, Pageable pageable);
	
	@Query("select g from GasStation g join fetch g.prices prices join fetch g.address address where UPPER(address.city) = ?2 and prices.ethanol <= ?1")
	public Slice<GasStation> findByPriceOfEthanol(Double price, String city, Pageable pageable);
	
	@Override
	@Query("select g from GasStation g join fetch g.prices join fetch g.address")
	public List<GasStation> findAll();

}
