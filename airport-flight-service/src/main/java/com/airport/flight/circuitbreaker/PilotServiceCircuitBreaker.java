/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airport.flight.feignclients.PilotFeignClient;
import com.airport.flight.response.PilotResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

/* writing a circuit breaker for downstream airport-pilot-service so if the airport-pilot-service is down we send a dummy response as its result */

@Slf4j
@Component
public class PilotServiceCircuitBreaker {

	@Autowired
	PilotFeignClient pilotFeignClient;
	
	public static final String PILOT_SERVICE_IS_DOWN_AT_THE_MOMENT = "PILOT_SERVICE_IS_DOWN_AT_THE_MOMENT";

	@CircuitBreaker(name = "airportPilotService", fallbackMethod = "fallbackGetPilotById")
	public PilotResponse getPilotById(long pilotId) {
		return pilotFeignClient.getPilotById(pilotId).getBody(); //NOSONAR
	}

	/* sending a dummy response if airport-pilot-service is down */
	public PilotResponse fallbackGetPilotById(long pilotId, Throwable th) {
		log.error("Airport Pilot Service is down at the moment ! " + th);
		
		/* dummy object */
		PilotResponse pilotResponse = new PilotResponse();
		pilotResponse.setId(pilotId);
		pilotResponse.setName(PILOT_SERVICE_IS_DOWN_AT_THE_MOMENT);
		pilotResponse.setExperience(PILOT_SERVICE_IS_DOWN_AT_THE_MOMENT);
		pilotResponse.setDesignation(PILOT_SERVICE_IS_DOWN_AT_THE_MOMENT);
		
		return pilotResponse;
	}
}