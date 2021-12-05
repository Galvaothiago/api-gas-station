package com.gasstation.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasstation.api.model.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
