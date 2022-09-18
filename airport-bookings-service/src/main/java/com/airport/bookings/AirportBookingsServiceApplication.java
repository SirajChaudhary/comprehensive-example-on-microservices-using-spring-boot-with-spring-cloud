package com.airport.bookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.airport.bookings.controller", "com.airport.bookings.service", "com.airport.bookings.exception",
		"com.airport.bookings.email", "com.airport.bookings.util" })
@EntityScan("com.airport.bookings.entity")
@EnableJpaRepositories("com.airport.bookings.repository")
@EnableFeignClients("com.airport.bookings.feignclients")
@EnableEurekaClient
public class AirportBookingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportBookingsServiceApplication.class, args);
	}
}