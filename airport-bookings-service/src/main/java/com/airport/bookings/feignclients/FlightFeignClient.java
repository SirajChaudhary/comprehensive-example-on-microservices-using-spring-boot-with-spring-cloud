/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.airport.bookings.response.FlightResponse;

/* api-gateway is the URL of api-gateway. we can check it on the eureka server console */

/* airport-flight-service is the URL of airport-flight-service. we can check it on the eureka server console */

/* 
 * if you want airport-bookings-service to call directly to airport-flight-service rather going via api-gateway 
 * then please change following value from "api-gatway/airport-flight-service" to "airport-flight-service"
 * 
 * it will be best practice if one microservice call other microservice via api-gateway (and not direct call) 
 * so source microservice will hit cross cutting concerns (e.g login credential check) written in api-gateway before calling the destination microservice
 */

@FeignClient(value = "api-gateway/airport-flight-service")
public interface FlightFeignClient {

	/*
	 * you can copy paste this method without body as below from calling
	 * airport-flight-service service
	 */

	@GetMapping("/api/v1/flights/{id}")
	public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id);
}