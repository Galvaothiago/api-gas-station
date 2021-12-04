package com.gasstation.api.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gasstation.api.model.entities.GasPrice;
import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.repositories.GasStationRepository;
import com.gasstation.api.repositories.PriceRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private GasStationRepository gasStationRepository;
	
	@Override
	public void run(String... args) throws Exception {		
		GasPrice price1 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, Instant.now());
		GasPrice price2 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, Instant.now());
		
		
		GasStation company1 = new GasStation(null, "Posto ATMA", "https://amazons3.com/fdgkgjdfhdflfn", price2);
		GasStation company2 = new GasStation(null, "Posto CAPELA", "https://amazons3.com/DFGNIFNBSDF46", price1);
		
		
		priceRepository.saveAll(Arrays.asList(price1, price2));
		gasStationRepository.saveAll(Arrays.asList(company1, company2));
	}

}
