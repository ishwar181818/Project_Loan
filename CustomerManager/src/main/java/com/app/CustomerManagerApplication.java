package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableDiscoveryClient
@SpringBootApplication
public class CustomerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagerApplication.class, args);
	}
	
	
	
	@Bean
	public RestTemplate rt()
	
	{
		
		
		return new RestTemplate();
		
	}

}
