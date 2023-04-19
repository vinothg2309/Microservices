package com.reactivetest.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.reactivetest.model.Account;

import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account, String>{

	Flux<Account> findByCustomerId(String customerId);
	
}
