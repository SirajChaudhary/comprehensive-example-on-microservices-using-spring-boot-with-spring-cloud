/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.airport.flight.response.PilotResponse;

/* api-gateway is the URL of api-gateway. we can check it on the eureka server console */

/* airport-pilot-service is the URL of airport-pilot-service. we can check it on the eureka server console */

/* note: the airport-flight-service do not call airport-pilot-service directly rather it call via api-gateway */

/* 
 * if you want airport-flight-service to call directly to airport-pilot-service rather going via api-gateway 
 * then please change following value from "api-gatway/airport-pilot-service" to "airport-pilot-service"
 *
 * it will be best practice if one microservice call other microservice via api-gateway (and not direct call) 
 * so source microservice will hit cross cutting concerns (e.g login credential check) written in api-gateway before calling the destination microservice
 */

@FeignClient(value = "api-gateway/airport-pilot-service")
public interface PilotFeignClient {

	@GetMapping("/api/v1/pilots/{id}")
	public ResponseEntity<PilotResponse> getPilotById(@PathVariable Long id);
}