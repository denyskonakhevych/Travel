package com.epam.traveling.entities;

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
@Table(name="HOTEL")
@ManagedBean(name = "hotel")
@RequestScoped
@NamedQueries({
    /*@NamedQuery(name = "Hotel.findByCountryId", query = "SELECT h FROM Hotel h WHERE h.country.id = ?1"),
    @NamedQuery(name = "Hotel.findByCountry", query = "SELECT h FROM Hotel h WHERE h.country = ?1"),*/
    @NamedQuery(name = "Hotel.findByName", query = "SELECT h FROM Hotel h WHERE h.name = ?1")
})
public class Hotel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOTEL_ID")
	private Integer id;
	
	@ManyToOne(/*cascade=CascadeType.ALL,*/ fetch=FetchType.EAGER )
	@JoinColumn(name = "CITY_ID")
	private City city;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="STARS")
	private int stars;
	
	@Column(name="DESCRIPTION")
	private String description;

	public Hotel() {
	}

	public Hotel(Integer id, City city, String name, int stars,
			String description) {
		super();
		this.id = id;
		this.city = city;
		this.name = name;
		this.stars = stars;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", city=" + city + ", name=" + name
				+ ", stars=" + stars + ", description=" + description + "]";
	}
}
