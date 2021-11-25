package com.example.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
