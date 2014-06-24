package com.epam.traveling.entities;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
@ManagedBean(name = "city")
@RequestScoped
@NamedQueries({
		/*@NamedQuery(name = "Hotel.findByCountryId", query = "SELECT h FROM Hotel h WHERE h.country.id = ?1"),
		@NamedQuery(name = "Hotel.findByCountry", query = "SELECT h FROM Hotel h WHERE h.country = ?1"),*/
		@NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name = ?1"),
		@NamedQuery(name = "City.findByCountry", query = "SELECT c FROM City c WHERE c.country = ?1")
})
public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1032019965737962897L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_ID")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	public City() {
	}

	public City(Integer id, Country country, String name, String description) {
		super();
		this.id = id;
		this.country = country;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

	@Override
	public String toString() {
		return "City [id=" + id + ", country=" + country + ", name=" + name
				+ ", description=" + description + "]";
	}
}
