package com.cassociation.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.FetchType;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cassociation.demo.onetomany.entities.Customer;
import com.cassociation.demo.onetomany.entities.PhoneNumber;
import com.cassociation.demo.onetomany.repository.CustomerRepo;

@SpringBootTest
class AssociationApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	CustomerRepo repo;
	
	@Test
	public void testCreateCustomer() {
		
		Customer c = new Customer();
		c.setName("LMN");
		
		//HashSet<PhoneNumber> phnum = new HashSet<PhoneNumber>();
		// above code is commented since we have used better approach for Foreign Key
		
		PhoneNumber ph = new PhoneNumber();
		ph.setPhnumber("9850353592");
		ph.setType("Home Phone");
		
		//foreign key will be not null after below execution
		//ph.setCustomer(c);
		
		//ph.setCustomer(c);;
		// above ph.setCustomer(c) and ph.setCustomer(c); is commented since we have used better approach for Foreign Key
		
		
		PhoneNumber ph2 = new PhoneNumber();
		ph2.setPhnumber("1234567890");
		ph2.setType("Office Phone");
		
		//foreign key will be not null after below execution
		//ph2.setCustomer(c);
		
		//phnum.add(ph2);
		// above ph2.setCustomer(c) and ph2.setCustomer(c); is commented since we have used better approach for Foreign Key
		
		//c.setPhnumber(phnum);
		//above code is commented since we have used better approach for Foreign Key
		
		// Now u can simply invoke "addPhoneNumber" method which we have declared in Customer class
		c.addPhoneNumber(ph);
		c.addPhoneNumber(ph2);
		
		repo.save(c);
	}
	
	// Read or Fetch details from table
	@Test
	//that is required for lazy loading to work (fetch = FetchType.EAGER).
	//@Transactional
	public void testRead() {
	
		Customer p1 = repo.findById(4).get();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + p1.getName());
		
		Set<PhoneNumber> num = p1.getPhnumber();
		num.forEach(n -> System.out.println(">>>>>>>>>" + n.getPhnumber()));
		
	}
	
	
	// Update data
	@Test
	public void testUpdateCustomer() {
	
		Customer p1 = repo.findById(4).get();
		p1.setName("ZZZZZZZZZZZ");
		
		Set<PhoneNumber> num = p1.getPhnumber();
		num.forEach(n -> n.setType("Home Phone"));
		
		//So we have a cascading effect on updates as well you need not save phone numbers separately when you save the customer.
		//Automatically everything got saved or updated.
		repo.save(p1);
		
	}

	//Delete data
	
	@Test
	public void testDelete() {
		repo.deleteById(4);;
	}
	
	
	
}
