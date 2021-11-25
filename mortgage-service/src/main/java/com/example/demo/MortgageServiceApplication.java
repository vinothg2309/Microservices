package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
//@RefreshScope
@RestController
@EnableJpaRepositories ("com.example.demo.repository")
public class MortgageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgageServiceApplication.class, args);
	}

}
