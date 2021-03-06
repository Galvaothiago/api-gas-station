package com.gasstation.api.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class GasStation  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	private String urlImg;
	
	@NotNull
	private boolean hasConvenience; 

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "gasStation", cascade = CascadeType.ALL)
	private GasPrice prices;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "gasStation", cascade = CascadeType.ALL)
	private Address address;
	
	public GasStation() {
		
	}
	
	public GasStation(Long id, String name, String urlImg, boolean hasConvenience) {
		this.id = id;
		this.name = name;
		this.urlImg = urlImg;
		this.hasConvenience = hasConvenience;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	
	public String getUrlImg() {
		return urlImg;
	}
	
	public GasPrice getPrices() {
		return prices;
	}

	public void setPrices(GasPrice prices) {
		this.prices = prices;
	}
	
	public void receiveGasPrice(GasPrice prices) {
		this.prices = prices;
		prices.receiveGasStation(this);
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void receiveAddress(Address address) {
		this.address = address;
		address.receiveGasStation(this);
	}

	public boolean isThereConvenience() {
		return hasConvenience;
	}

	public void setThereConvenience(boolean isThereConvenience) {
		this.hasConvenience = isThereConvenience;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GasStation other = (GasStation) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return name + "ID: " + id;
		
	}
}
