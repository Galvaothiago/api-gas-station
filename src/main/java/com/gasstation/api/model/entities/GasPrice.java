package com.gasstation.api.model.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class GasPrice implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double gasoline;
	private Double ethanol;
	private Double diesel;
	private Double gasolineAdditive;
	private Double ethanolAdditive;
	private Double relation_gas_ethanol;
	private Instant updatedAt;
	private Double lastGasPrice;
	private Double lastEthanolPrice;

	@JsonIgnore
	@OneToOne
	@MapsId
	GasStation gasStation;
	
	public GasPrice() {
		
	}
		
	public GasPrice(Long id, Double gasoline, Double ethanol, Double diesel, Double gasolineAdditive,
			Double ethanolAdditive, GasStation gasStation, Instant updatedAt) {
		this.id = id;
		this.gasoline = gasoline;
		this.ethanol = ethanol;
		this.diesel = diesel;
		this.gasolineAdditive = gasolineAdditive;
		this.ethanolAdditive = ethanolAdditive;
		this.gasStation = gasStation;
		this.updatedAt = updatedAt;
		this.lastEthanolPrice = 0.00D;
		this.lastGasPrice = 0.00D;
		setRelationGasEthanol();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
 
	public Double getGasoline() {
		return gasoline;
	}

	public void setGasoline(Double gasoline) {
		this.gasoline = gasoline;
	}

	public Double getEthanol() {
		return ethanol;
	}

	public void setEthanol(Double ethanol) {
		this.ethanol = ethanol;
	}

	public Double getDiesel() {
		return diesel;
	}

	public void setDiesel(Double diesel) {
		this.diesel = diesel;
	}

	public Double getGasolineAdditive() {
		return gasolineAdditive;
	}

	public void setGasolineAdditive(Double gasolineAdditive) {
		this.gasolineAdditive = gasolineAdditive;
	}

	public Double getEthanolAdditive() {
		return ethanolAdditive;
	}

	public void setEthanolAdditive(Double ethanolAdditive) {
		this.ethanolAdditive = ethanolAdditive;
	}

	public Instant getupdatedAt() {
		return updatedAt;
	}

	public void setupdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public GasStation getGasStation() {
		return gasStation;
	}

	public void setGasStation(GasStation gasStation) {
		this.gasStation = gasStation;
		gasStation.receiveGasPrice(this);
	}
	
	public void receiveGasStation(GasStation gasStation) {
		this.gasStation = gasStation;
	}
	
	public void setRelationGasEthanol() {
		Double gasoline = getGasoline();
		Double ethanol = getEthanol();
		
		Double value = Double.valueOf(String.format("%.2f", (ethanol / gasoline)));
		
		this.relation_gas_ethanol = value;
	}
	
	public Double getRelationGasEthanol() {
		return this.relation_gas_ethanol;
	}
	
	public Double getLastGasPrice() {
		return lastGasPrice;
	}

	public void setLastGasPrice(Double lastGasPrice) {
		this.lastGasPrice = lastGasPrice;
	}

	public Double getLastEthanolPrice() {
		return lastEthanolPrice;
	}

	public void setLastEthanolPrice(Double lastEthanolPrice) {
		this.lastEthanolPrice = lastEthanolPrice;
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
		GasPrice other = (GasPrice) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
