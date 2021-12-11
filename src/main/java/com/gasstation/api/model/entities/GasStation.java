package com.gasstation.api.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class GasStation  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String urlImg;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prices_id", unique = true)
	private GasPrice prices;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", unique = true)
	private Address address;
	
	public GasStation() {
		
	}
	
	public GasStation(Long id, String name, String urlImg, GasPrice prices, Address address) {
		this.id = id;
		this.name = name;
		this.urlImg = urlImg;
		this.prices = prices;
		this.address = address;
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
	
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
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
