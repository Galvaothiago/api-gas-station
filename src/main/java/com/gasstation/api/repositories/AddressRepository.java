package com.gasstation.api.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gasstation.api.model.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	public Slice<Address> findByStreetContainingIgnoreCase(String streetName, Pageable page);
	
	public Slice<Address> findByCityContainingIgnoreCase(String cityName, Pageable page);
}
