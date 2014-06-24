package com.epam.traveling.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ANALITIC")
@ManagedBean
@RequestScoped
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Analitic.findByEmailAndPassword", query = "SELECT a FROM Analitic a WHERE a.email = ?1 AND a.password = ?2"),
    @NamedQuery(name = "Analitic.findByEmail", query = "SELECT a FROM Analitic a WHERE a.email = ?1")
})
public class Analitic extends Person {
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="SECOND_NAME")
	private String secondName;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name = "POSSITION")
	private String possition;

	public Analitic() {
	}
	
	public Analitic(String email, String password, String firstName, String secondName, String phoneNumber,
			String possition) {
		super(email, password);
		this.firstName = firstName;
		this.secondName = secondName;
		this.phoneNumber = phoneNumber;
		this.possition = possition;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPossition() {
		return possition;
	}

	public void setPossition(String possition) {
		this.possition = possition;
	}

	@Override
	public String toString() {
		return "Analitic [" + super.toString() + ", firstName=" + firstName + ", secondName="
				+ secondName + ", phoneNumber=" + phoneNumber + ", possition="
				+ possition + "]";
	}
}
