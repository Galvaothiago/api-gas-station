package com.gasstation.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasstation.api.model.entities.GasPrice;

public interface PriceRepository extends JpaRepository<GasPrice, Long>{

}
