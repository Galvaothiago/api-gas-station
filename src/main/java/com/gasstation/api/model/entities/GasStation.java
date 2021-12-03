package com.gasstation.api.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GasStation  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String urlImg;
	
	public GasStation() {
		
	}
	
	public GasStation(Long id, String name, String urlImg) {
		this.id = id;
		this.name = name;
		this.urlImg = urlImg;
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
