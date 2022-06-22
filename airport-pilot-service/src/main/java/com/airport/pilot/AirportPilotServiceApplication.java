package com.airport.pilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({ "com.airport.pilot.controller", "com.airport.pilot.service", "com.airport.pilot.exception",
		"com.airport.pilot.actuator" })
@EntityScan("com.airport.pilot.entity")
//@EnableJpaRepositories("com.airport.pilot.repository")
@EnableEurekaClient
@EnableCaching(order = 1)
@EnableTransactionManagement(order = 2)
public class AirportPilotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportPilotServiceApplication.class, args);
	}
}