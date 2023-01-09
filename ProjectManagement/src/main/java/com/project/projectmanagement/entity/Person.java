package com.project.projectmanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name ="PERSONS")
public class Person {

	@Id //to define an identifier
	@Column(name = "person_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private  Integer personId;
	@Column(name = "first_name", nullable = false)
	private  String firstName;
	@Column(name = "last_name",nullable = false)
	private String lastName;
	@Column(name = "email_id")
	private String emailID;
	@Column(name = "phone_number")
	private String phoneNumber;
	
	//foreign key - primary key
	@JsonIgnore
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Login login;
	
	//getters and setters
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public  String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	//constructors
	public Person() {
		
	}
	public Person(String firstName, String lastName, String emailID, String string) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.phoneNumber = string;
	}
}
