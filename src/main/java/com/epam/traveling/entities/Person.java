package com.epam.traveling.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PERSON_ID")
	protected Integer personId;
	
	@Column(name="EMAIL", unique=true)
	protected String email;
	
	@Column(name="PASSWORD")
	protected String password;
	
	@Column(name="SALT")
	protected String salt;

	public Person() {
	}

	public Person(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", email=" + email
				+ ", password=" + password + "]";
	}
}
