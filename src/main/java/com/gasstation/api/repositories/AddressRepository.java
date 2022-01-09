package com.gasstation.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gasstation.api.model.CountingCity;
import com.gasstation.api.model.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	@Query("select new com.gasstation.api.model.CountingCity(a.city, count(*)) from Address a where a.city = ?1 group by city")
	public CountingCity findCountCity(String city);
	
	@Query("select new com.gasstation.api.model.CountingCity(a.city, count(*)) from Address a group by city")
	public Iterable<CountingCity> countAllCity();
	
}
