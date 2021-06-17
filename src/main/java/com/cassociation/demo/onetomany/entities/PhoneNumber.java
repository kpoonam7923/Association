package com.cassociation.demo.onetomany.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "phone_number")
public class PhoneNumber {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String phnumber;
	private String type;
	
	// ******************* MANY TO ONE ASSOCIATION ******************
	@ManyToOne
	//to join this customer to the customer to this phone number this phone number to a particular customer. 
	//We need to use at join in column
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhnumber() {
		return phnumber;
	}
	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
