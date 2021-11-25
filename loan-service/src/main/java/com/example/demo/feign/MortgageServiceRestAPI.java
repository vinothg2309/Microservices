package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.MortgageDetail;

@FeignClient(name="mortgage-service", fallbackFactory=MortgageServiceFallbackFactory.class)
public interface MortgageServiceRestAPI {
	
	@GetMapping("/fetchMortgage/{custnumber}")
	MortgageDetail fetchMortgage (@PathVariable(value="custnumber") Integer custnumber);

	@GetMapping("/test") 
	String test();

}
