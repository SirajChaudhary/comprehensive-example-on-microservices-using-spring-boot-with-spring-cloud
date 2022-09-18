package com.airport.boarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.airport.boarding.controller", "com.airport.boarding.service" })
@EnableFeignClients("com.airport.boarding.feignclients")
@EnableEurekaClient
public class AirportBoardingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportBoardingServiceApplication.class, args);
	}
}