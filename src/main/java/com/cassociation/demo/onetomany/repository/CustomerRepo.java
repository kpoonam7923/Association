package com.cassociation.demo.onetomany.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cassociation.demo.onetomany.entities.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

	//List<Customer> findByIdIn(List<Customer> id);
}
