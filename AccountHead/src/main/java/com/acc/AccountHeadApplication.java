package com.acc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class AccountHeadApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountHeadApplication.class, args);
	}

}
