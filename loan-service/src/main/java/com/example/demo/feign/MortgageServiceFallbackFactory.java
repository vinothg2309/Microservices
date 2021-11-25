package com.example.demo.feign;

import org.springframework.stereotype.Service;

import com.example.demo.model.MortgageDetail;

import feign.hystrix.FallbackFactory;

@Service
public class MortgageServiceFallbackFactory implements FallbackFactory<MortgageServiceRestAPI>{

	@Override
	public MortgageServiceRestAPI create(Throwable cause) {
		return new MortgageServiceRestAPI() {
			
			@Override
			public String test() {
				return "Unable to fetch the result. Reponse from fallback method";
			}
			
			@Override
			public MortgageDetail fetchMortgage(Integer custnumber) {
				MortgageDetail mortgageDetail = new MortgageDetail();
				mortgageDetail.setStatus("Unable to fetch the result. Reponse from fallback method");
				return mortgageDetail;
			}
		};
	}

}
