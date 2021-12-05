package com.gasstation.api.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gasstation.api.model.entities.Address;
import com.gasstation.api.model.entities.GasPrice;
import com.gasstation.api.model.entities.GasStation;
import com.gasstation.api.repositories.AddressRepository;
import com.gasstation.api.repositories.GasStationRepository;
import com.gasstation.api.repositories.PriceRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private GasStationRepository gasStationRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void run(String... args) throws Exception {		
		GasPrice price1 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, Instant.now());
		GasPrice price2 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, Instant.now());
		GasPrice price3 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, Instant.now());
		GasPrice price4 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, Instant.now());
		GasPrice price5 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, Instant.now());
		GasPrice price6 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, Instant.now());
		
		Address address1 = new Address(null, "Rua maria pereira", "112", "Vinhedo");
		Address address2 = new Address(null, "Rua Vinhedo", "112", "Vinhedo");
		Address address3 = new Address(null, "Avenida Independencia", "2135", "Vinhedo");
		Address address4 = new Address(null, "Rua Regina Giunco", "778", "Vinhedo");
		Address address5 = new Address(null, "Rua Osasco 2", "12", "Vinhedo");
		Address address6 = new Address(null, "Avenida Rufino norte", "889", "Vinhedo");
		
		
		GasStation company1 = new GasStation(null, "Posto ATMA", "https://amazons3.com/fdgkgjdfhdflfn", price2, address3);
		GasStation company2 = new GasStation(null, "Posto CAPELA", "https://amazons3.com/DFGNIFNBSDF46", price1, address1);
		GasStation company3 = new GasStation(null, "Posto Estrela", "https://amazons3.com/fdgkgjdfhdflfn", price4, address4);
		GasStation company4 = new GasStation(null, "Posto nova palmares", "https://amazons3.com/DFGNIFNBSDF46", price5, address2);
		GasStation company5 = new GasStation(null, "Posto Preço bom", "https://amazons3.com/fdgkgjdfhdflfn", price3, address6);
		GasStation company6 = new GasStation(null, "Posto praça 2", "https://amazons3.com/DFGNIFNBSDF46", price6, address5);
		
		
		priceRepository.saveAll(Arrays.asList(price1, price2, price3, price4, price5, price6));
		addressRepository.saveAll(Arrays.asList(address1, address2, address3, address5, address6, address4));
		gasStationRepository.saveAll(Arrays.asList(company1, company2, company3, company4, company5, company6));
	}

}
