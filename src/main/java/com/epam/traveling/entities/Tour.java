package com.epam.traveling.entities;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
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
@Table(name="TOUR")
@ManagedBean(name = "tour")
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "Tour.getAvaliable", query = "SELECT t FROM Tour t WHERE t.status = 'avaliable'")
})
public class Tour {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TOUR_ID")
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "HOTEL_ID")
	private Hotel hotel;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "DEPARTUE_CITY_ID")
	private City departueFrom;
	
	@Column(name = "DEPARTUE_FROM_DATE")
	private Date departueDate;
	
	@Column(name = "NUMBER_OF_NIGHTS")
	private int numberOfNights;
	
	@Column(name = "TYPE_OF_ROOM")
	private String typeOfRoom;
	
	@Column(name = "MAX_NUMBER_OF_ADULTS")
	private int maxNumberOfAdults;
	
	@Column(name = "MAX_NUMBER_OF_CHILDREN")
	private int maxNumberOfChildren;
	
	@Column(name = "TYPE_OF_FOOD")
	private String typeOfFood;
	
	@Column(name = "PRICE")
	private float price;
	
	@Column(name = "STATUS")
	private String status;

	public Tour() {
	}

	public Tour(Integer id, Hotel hotel, City departueFrom, Date dapartueDate,
			int numberOfNights, String typeOfRoom, int maxNumberOfAdults,
			int maxNumberOfChildren, String typeOfFood, String status, float price) {
		this.id = id;
		this.hotel = hotel;
		this.departueFrom = departueFrom;
		this.departueDate = dapartueDate;
		this.numberOfNights = numberOfNights;
		this.typeOfRoom = typeOfRoom;
		this.maxNumberOfAdults = maxNumberOfAdults;
		this.maxNumberOfChildren = maxNumberOfChildren;
		this.typeOfFood = typeOfFood;
		this.status = status;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public City getDepartueFrom() {
		return departueFrom;
	}

	public void setDepartueFrom(City departueFrom) {
		this.departueFrom = departueFrom;
	}

	public Date getDepartueDate() {
		return departueDate;
	}

	public void setDepartueDate(Date dapartueDate) {
		this.departueDate = dapartueDate;
	}

	public int getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

	public String getTypeOfRoom() {
		return typeOfRoom;
	}

	public void setTypeOfRoom(String typeOfRoom) {
		this.typeOfRoom = typeOfRoom;
	}

	public int getMaxNumberOfAdults() {
		return maxNumberOfAdults;
	}

	public void setMaxNumberOfAdults(int maxNumberOfAdults) {
		this.maxNumberOfAdults = maxNumberOfAdults;
	}

	public int getMaxNumberOfChildren() {
		return maxNumberOfChildren;
	}

	public void setMaxNumberOfChildren(int maxNumberOfChildren) {
		this.maxNumberOfChildren = maxNumberOfChildren;
	}

	public String getTypeOfFood() {
		return typeOfFood;
	}

	public void setTypeOfFood(String typeOfFood) {
		this.typeOfFood = typeOfFood;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((departueDate == null) ? 0 : departueDate.hashCode());
		result = prime * result
				+ ((departueFrom == null) ? 0 : departueFrom.hashCode());
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + maxNumberOfAdults;
		result = prime * result + maxNumberOfChildren;
		result = prime * result + numberOfNights;
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((typeOfFood == null) ? 0 : typeOfFood.hashCode());
		result = prime * result
				+ ((typeOfRoom == null) ? 0 : typeOfRoom.hashCode());
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
		Tour other = (Tour) obj;
		if (departueDate == null) {
			if (other.departueDate != null)
				return false;
		} else if (!departueDate.equals(other.departueDate))
			return false;
		if (departueFrom == null) {
			if (other.departueFrom != null)
				return false;
		} else if (!departueFrom.equals(other.departueFrom))
			return false;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxNumberOfAdults != other.maxNumberOfAdults)
			return false;
		if (maxNumberOfChildren != other.maxNumberOfChildren)
			return false;
		if (numberOfNights != other.numberOfNights)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (typeOfFood == null) {
			if (other.typeOfFood != null)
				return false;
		} else if (!typeOfFood.equals(other.typeOfFood))
			return false;
		if (typeOfRoom == null) {
			if (other.typeOfRoom != null)
				return false;
		} else if (!typeOfRoom.equals(other.typeOfRoom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tour [id=" + id + ", hotel=" + hotel + ", departueFrom="
				+ departueFrom + ", departueDate=" + departueDate
				+ ", numberOfNights=" + numberOfNights + ", typeOfRoom="
				+ typeOfRoom + ", maxNumberOfAdults=" + maxNumberOfAdults
				+ ", maxNumberOfChildren=" + maxNumberOfChildren
				+ ", typeOfFood=" + typeOfFood + ", price=" + price
				+ ", status=" + status + "]";
	}
}
