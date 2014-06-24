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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USER")
@XmlRootElement
@ManagedBean
@RequestScoped
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "User.findByEmailAndPassword", query = "SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = ?1")
})
public class User extends Person {
	
	@Column(name="FIRST_NAME")
	protected String firstName;
	
	@Column(name="SECOND_NAME")
	protected String secondName;
	
	@Column(name="PHONE_NUMBER")
	protected String phoneNumber;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="ADDRESS")
	private String address;

	public User() {
	}

	public User(String email, String password, String firstName, String secondName, String phoneNumber,
			String gender, String address) {
		super(email, password);
		this.firstName = firstName;
		this.secondName = secondName;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.address = address;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result
				+ ((secondName == null) ? 0 : secondName.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User [" + super.toString() + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", phoneNumber=" + phoneNumber + ", gender=" + gender
				+ ", address=" + address + "]";
	}
	
}
