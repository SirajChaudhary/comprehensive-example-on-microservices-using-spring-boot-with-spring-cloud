package com.airport.pilot.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/* https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.implementing-custom */

@Endpoint(id = "airport-pilot-service-custom-web-endpoint")
@Component
public class AiportPilotServiceCustomWebEndpoint {

	@ReadOperation
	@Bean
	public String greet() {
		return "Hello, I am Aiport Pilot Service";
	}
}