package com.airport.checkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.airport.checkin.controller", "com.airport.checkin.service", "com.airport.checkin.exception" })
@EntityScan("com.airport.checkin.entity")
@EnableJpaRepositories("com.airport.checkin.repository")
@EnableEurekaClient
public class AirportCheckinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportCheckinServiceApplication.class, args);
	}
}