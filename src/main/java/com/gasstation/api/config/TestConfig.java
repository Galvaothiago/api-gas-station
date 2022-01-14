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
	
		GasStation company1 = new GasStation(null, "Posto ATMA", "https://amazons3.com/fdgkgjdfhdflfn", true);
		GasStation company2 = new GasStation(null, "Posto CAPELA", "https://amazons3.com/DFGNIFNBSDF46", true);
		GasStation company3 = new GasStation(null, "Posto Estrela", "https://amazons3.com/fdgkgjdfhdflfn", true);
		GasStation company4 = new GasStation(null, "Posto nova palmares", "https://amazons3.com/DFGNIFNBSDF46", false);
		GasStation company5 = new GasStation(null, "Posto Preço bom", "https://amazons3.com/fdgkgjdfhdflfn", false);
		GasStation company6 = new GasStation(null, "Posto praça 2", "https://amazons3.com/DFGNIFNBSDF46", true);
		GasStation company7 = new GasStation(null, "Posto Test3", "https://amazons3.com/DFGNIFNBSDF46", false);
		GasStation company8 = new GasStation(null, "Posto Divino", "https://amazons3.com/fdgkgjdfhdflfn", false);
		GasStation company9 = new GasStation(null, "Posto Matão", "https://amazons3.com/DFGNIFNBSDF46", false);
		GasStation company10 = new GasStation(null, "Posto test4", "https://amazons3.com/DFGNIFNBSDF046", false);

		Address address1 = new Address(null, "Rua maria pereira", "112", "Vinhedo", company1);
		Address address2 = new Address(null, "Rua Vinhedo", "112", "Vinhedo", company2);
		Address address3 = new Address(null, "Avenida Independencia", "2135", "Vinhedo", company3);
		Address address4 = new Address(null, "Rua Regina Giunco", "778", "Vinhedo", company4);
		Address address5 = new Address(null, "Rua Osasco 2", "12", "Vinhedo", company5);
		Address address6 = new Address(null, "Avenida Rufino norte", "889", "Vinhedo", company6);
		Address address7 = new Address(null, "Avenida Matão", "1140", "Paulinia", company7);
		Address address8 = new Address(null, "Rua Independencia", "320", "Valinhos", company8);
		Address address9 = new Address(null, "Rua Oscar Remo", "5812", "Valinhos", company9);
		Address address10 = new Address(null, "Rua Pereira Barreto", "1889", "Paulinia", company10);
		
		
		GasPrice price1 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, company1, Instant.now(), "galvaothiago");
		GasPrice price2 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, company2, Instant.now(),"galvaothiago");
		GasPrice price3 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, company3, Instant.now(),"galvaothiago");
		GasPrice price4 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, company4, Instant.now(), "galvaothiago");
		GasPrice price5 = new GasPrice(null, 6.30, 4.80, 4.70, 6.40, 5.20, company5, Instant.now(), "galvaothiago");
		GasPrice price6 = new GasPrice(null, 6.52, 4.79, 4.55, 6.35, 5.50, company6, Instant.now(), "galvaothiago");
		GasPrice price7 = new GasPrice(null, 6.50, 4.88, 4.30, 6.55, 5.15, company7, Instant.now(), "galvaothiago");
		GasPrice price8 = new GasPrice(null, 6.62, 4.89, 4.95, 6.65, 5.50, company8, Instant.now(), "galvaothiago");
		GasPrice price9 = new GasPrice(null, 6.50, 4.75, 4.50, 6.90, 5.30, company9, Instant.now(), "galvaothiago");
		GasPrice price10 = new GasPrice(null, 6.39, 4.69, 4.55, 6.45, 5.10, company10, Instant.now(), "galvaothiago");
		
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
		
		company7.setAddress(address7);
		company7.setPrices(price7);
		
		company8.setAddress(address8);
		company8.setPrices(price8);
		
		company9.setAddress(address9);
		company9.setPrices(price9);
		
		company10.setAddress(address10);
		company10.setPrices(price10);
		
		gasStationRepository.saveAll(Arrays.asList(company1, company2, company3, company4, company5, company6, company7, company8, company9, company10));
	}

}
