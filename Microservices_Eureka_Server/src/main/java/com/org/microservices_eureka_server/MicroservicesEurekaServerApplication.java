package com.org.microservices_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class MicroservicesEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesEurekaServerApplication.class, args);
	}

}
