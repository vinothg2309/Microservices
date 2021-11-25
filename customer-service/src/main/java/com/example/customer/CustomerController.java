package com.example.customer;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;

@RestController

public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping(path="/saveCustomer")

	private Customer saveCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		return savedCustomer;
	}

	@GetMapping (path="/fetchAllCustomers") 
	private List<Customer> fetchAllCustomers(){

		List<Customer> customerlist = customerRepository.findAll();
		return customerlist; 
	}

	@GetMapping (path="/getCustomer/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
	private Customer getCustomer (@PathVariable Integer id) { 
		Customer customer = customerRepository.findById(id).get(); 
		return customer; 
	}
	
}