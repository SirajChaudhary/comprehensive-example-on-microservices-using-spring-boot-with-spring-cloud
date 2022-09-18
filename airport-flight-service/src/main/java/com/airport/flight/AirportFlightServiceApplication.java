package com.airport.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.airport.flight.controller", "com.airport.flight.service", "com.airport.flight.exception",
		"com.airport.flight.circuitbreaker" })
@EntityScan("com.airport.flight.entity")
@EnableJpaRepositories("com.airport.flight.repository")
@EnableFeignClients("com.airport.flight.feignclients")
@EnableEurekaClient
public class AirportFlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportFlightServiceApplication.class, args);
	}
}