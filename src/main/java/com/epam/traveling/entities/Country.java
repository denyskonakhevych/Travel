package com.epam.traveling.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY")
@NamedQueries({
    @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = ?1")
})
public class Country implements Serializable {
	
	private static final long serialVersionUID = 8480930154129774235L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COUNTRY_ID")
	private Integer id;
	
	@Column(name="NAME", unique=true)
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CLIMATE")
	private String climate;

	public Country() {
	}

	public Country(Integer id, String name, String description, String climate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.climate = climate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (climate == null) {
			if (other.climate != null)
				return false;
		} else if (!climate.equals(other.climate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", description="
				+ description + ", climate=" + climate + "]";
	}
}
