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
	private GasStationRepository gasStationRepository;
	
	@Override
	public void run(String... args) throws Exception {		
	
		GasStation company1 = new GasStation(null, "Posto ATMA", "https://amazons3.com/fdgkgjdfhdflfn");
		GasStation company2 = new GasStation(null, "Posto CAPELA", "https://amazons3.com/DFGNIFNBSDF46");
		GasStation company3 = new GasStation(null, "Posto Estrela", "https://amazons3.com/fdgkgjdfhdflfn");
		GasStation company4 = new GasStation(null, "Posto nova palmares", "https://amazons3.com/DFGNIFNBSDF46");
		GasStation company5 = new GasStation(null, "Posto Preço bom", "https://amazons3.com/fdgkgjdfhdflfn");
		GasStation company6 = new GasStation(null, "Posto praça 2", "https://amazons3.com/DFGNIFNBSDF46");
//		
					
		Address address1 = new Address(null, "Rua maria pereira", "112", "Vinhedo", company1);
		Address address2 = new Address(null, "Rua Vinhedo", "112", "Vinhedo", company2);
		Address address3 = new Address(null, "Avenida Independencia", "2135", "Vinhedo", company3);
		Address address4 = new Address(null, "Rua Regina Giunco", "778", "Vinhedo", company4);
		Address address5 = new Address(null, "Rua Osasco 2", "12", "Vinhedo", company5);
		Address address6 = new Address(null, "Avenida Rufino norte", "889", "Vinhedo", company6);
		
		GasPrice price1 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, company1, Instant.now());
		GasPrice price2 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, company2, Instant.now());
		GasPrice price3 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, company3, Instant.now());
		GasPrice price4 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, company4, Instant.now());
		GasPrice price5 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, company5, Instant.now());
		GasPrice price6 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, company6, Instant.now());
		
		company1.setAddress(address1);
		company1.setPrices(price1);
		
		company2.setAddress(address2);
		company2.setPrices(price2);
		
		company3.setAddress(address3);
		company3.setPrices(price3);
		
		company4.setAddress(address4);
		company4.setPrices(price4);
		
		company5.setAddress(address5);
		company5.setPrices(price5);
		
		company6.setAddress(address6);
		company6.setPrices(price6);
		
		

//		
		

//		
//		priceRepository.saveAll(Arrays.asList(price1, price2, price3, price4, price5, price6));
//		addressRepository.saveAll(Arrays.asList(address1));
		gasStationRepository.saveAll(Arrays.asList(company1, company2, company3, company4, company5, company6));
	}

}
