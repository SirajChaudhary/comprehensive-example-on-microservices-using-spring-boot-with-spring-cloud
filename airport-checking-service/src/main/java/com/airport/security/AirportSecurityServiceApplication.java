package com.airport.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.airport.security.controller", "com.airport.security.service", "com.airport.security.exception" })
@EntityScan("com.airport.security.entity")
@EnableJpaRepositories("com.airport.security.repository")
@EnableEurekaClient
public class AirportSecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportSecurityServiceApplication.class, args);
	}
}