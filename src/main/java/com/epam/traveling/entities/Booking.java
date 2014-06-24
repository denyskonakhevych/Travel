package com.epam.traveling.entities;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="BOOKING")
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "Booking.complete", query = "UPDATE Booking b SET b.status = 'completed' WHERE b = ?1"),
    @NamedQuery(name = "Booking.findByUser", query = "SELECT b FROM Booking b WHERE b.orderedBy = ?1")
})
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BOOKING_ID")
	private Integer id;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "BOOKING_ID_FK", referencedColumnName = "BOOKING_ID")
	private List<Tour> tours;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User orderedBy;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "ANALITIC_ID")
	private Analitic managedBy;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="TOTAL_PRICE")
	private float totalPrice;

	public Booking() {
		super();
	}

	public Booking(Integer id, List<Tour> tours, User orderedBy, Analitic managedBy,
			String status, float totalPrice) {
		super();
		this.id = id;
		this.tours = tours;
		this.orderedBy = orderedBy;
		this.managedBy = managedBy;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public User getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(User orderedBy) {
		this.orderedBy = orderedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Analitic getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(Analitic managedBy) {
		this.managedBy = managedBy;
	}

//	@Override
//	public String toString() {
//		return "Booking [id=" + id + ", orderedBy=" + orderedBy
//				+ ", managedBy=" + managedBy + ", status=" + status
//				+ ", totalPrice=" + totalPrice + "]";
//	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", tours=" + tours + ", orderedBy="
				+ orderedBy + ", managedBy=" + managedBy + ", status=" + status
				+ ", totalPrice=" + totalPrice + "]";
	}
	
	
}
