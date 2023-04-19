package com.reactivetest.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.reactivetest.model.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String>{

}
