package com.gasstation.api.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	private String number;
	private String city;
	private String state;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	private GasStation gasStation;
	
	public Address() {
		
	}
	
	public Address(Long id, String street, String number, String city, String state, GasStation gasStation) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.city = city;
		this.state = state;
		this.gasStation = gasStation;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public GasStation getGasStation() {
		return gasStation;
	}

	public void setGasStation(GasStation gasStation) {
		this.gasStation = gasStation;
		gasStation.receiveAddress(this);
	}
	
	public void receiveGasStation(GasStation gasStation) {
		this.gasStation = gasStation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, id, number, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(id, other.id) && Objects.equals(number, other.number)
				&& Objects.equals(street, other.street);
	}
	
	@Override
	public String toString() {
		return street + ", " + number + " - " + city + ", " + state;
	}
	
	
}
