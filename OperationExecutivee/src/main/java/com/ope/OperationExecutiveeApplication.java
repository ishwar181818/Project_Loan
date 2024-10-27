package com.ope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class OperationExecutiveeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationExecutiveeApplication.class, args);
	}

}
