package com.example.demo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication 
@EnableEurekaClient
@EnableZuulProxy
@RestController

public class ApiGatewayApplication {

	@Autowired
	private DiscoveryClient discoveryClient;

	public static void main(String[] args) { 
		SpringApplication.run(ApiGatewayApplication.class, args); 
	}

	@Bean
	public CorsFilter corsFilter() { 
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		final CorsConfiguration config = new CorsConfiguration(); 
		config.setAllowCredentials (true);
		config.addAllowedOrigin(""); 
		config.addAllowedHeader("");
		config.addAllowedMethod ("OPTIONS");
		config.addAllowedMethod("HEAD"); 
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod ("DELETE");
		config.addAllowedMethod ("PATCH");
		source.registerCorsConfiguration ("/", config);

		return new CorsFilter(source);
	}

	@GetMapping("/get-all-apps")
	public List<InstanceInfo> testing() throws UnknownHostException{

		StringBuilder sb = new StringBuilder(); 
		List<String> servicelist = discoveryClient.getServices();
		List<InstanceInfo> instances = new ArrayList<>();
		for (String service: servicelist)
		{
			List<ServiceInstance> instancelist = discoveryClient.getInstances(service);
			for (ServiceInstance insInfo: instancelist){
				InstanceInfo info = new InstanceInfo(); 
				String instanceId  = insInfo.getInstanceId();
				info.setInstanceId(instanceId); 
				info.setPort(insInfo.getPort());
				info.setAppName(instanceId.split(":")[1]); 
				sb.append(insInfo.getInstanceId()).append("--PORT--").append(insInfo.getPort()).append("\n");
				instances.add(info);
			}
		}
		return instances;
	} 
}