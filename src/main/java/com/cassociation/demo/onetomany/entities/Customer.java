package com.cassociation.demo.onetomany.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer_details")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	
	// ******************* ONE TO MANY ASSOCIATION ******************
	//the primary key is in the customer and it owns the mapping
	//cascade affects what we do on customer, Also do it on phone numbers for inserts updates deletes etc
	//let's bring back fetch type eager just to make sure that we are not dealing with any additional transactionality in our test method
	//EAGER which will fetch the data right away as soon as the parent is loaded. byDefault it is fetch = FetchType.LAZY
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	
	//field type of "set", a collection of phone numbers because a customer can have multiple phone numbers
	private Set<PhoneNumber> phnumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<PhoneNumber> getPhnumber() {
		return phnumber;
	}
	public void setPhnumber(Set<PhoneNumber> phnumber) {
		this.phnumber = phnumber;
	}
	
	// ********** BETTER APPROACH FOR FOREIGN KEY **************
	
	//This method will be invoked for as many phone numbers as we want we can't pass in 
	//and those will be added to this particular collection here.
	
	public void addPhoneNumber(PhoneNumber number) {
		//Will add one phone number at that time to the set (private Set<PhoneNumber> phnumber {check above set written}) which is in here. 
		//So this number set will be updating continuously and our clients can call this method the users of our entities can call this method.
		
		if(number != null) {
			if(phnumber == null) {
				phnumber = new HashSet<>();
			}
			//set customer so that we allow the foreign key to do this on whichever customer object this method is being invoked.
			number.setCustomer(this);
			phnumber.add(number);
		}
	}

	// We have new records with the ID 4 with the foreign key "4", the better 
	//and cleaner way is to add "addPhoneNumber()" for the child object on the parent entity which is the customer.	
	
	
}
