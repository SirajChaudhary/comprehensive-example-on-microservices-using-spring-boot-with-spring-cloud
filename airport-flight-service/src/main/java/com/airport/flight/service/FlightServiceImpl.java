/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airport.flight.circuitbreaker.PilotServiceCircuitBreaker;
import com.airport.flight.entity.FlightEntity;
import com.airport.flight.exception.FlightNotFoundException;
import com.airport.flight.feignclients.PilotFeignClient;
import com.airport.flight.repository.FlightRepository;
import com.airport.flight.request.Flight;
import com.airport.flight.response.FlightResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PilotFeignClient pilotFeignClient;

	@Autowired
	PilotServiceCircuitBreaker pilotServiceCircuitBreaker;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public FlightResponse createFlight(Flight flight) {

		log.info("starting createFlight() service method");

		/* map flight input object to flight entity object automatically */
		FlightEntity flightEntity = modelMapper.map(flight, FlightEntity.class);

		flightEntity = flightRepository.save(flightEntity);

		FlightResponse flightResponse = new FlightResponse(flightEntity);

		/*
		 * lets consider downstream airport-pilot-service is slow or down therefore
		 * calling airport-pilot-service using our defined circuit breaker
		 */
		flightResponse.setPilotResponse(pilotServiceCircuitBreaker.getPilotById(flightEntity.getPilotId()));

		return flightResponse;
	}

	@Override
	public FlightResponse getFlightById(long id) {

		log.info("starting getFlightById() service method");

		Optional<FlightEntity> optionalFlightEntity = flightRepository.findById(id);

		FlightEntity flightEntity = null;

		if (optionalFlightEntity.isPresent())
			flightEntity = optionalFlightEntity.get();

		FlightResponse flightResponse = new FlightResponse(flightEntity);

		/*
		 * lets consider downstream airport-pilot-service is slow or down therefore
		 * calling airport-pilot-service using our defined circuit breaker
		 */
		flightResponse.setPilotResponse(pilotServiceCircuitBreaker.getPilotById(flightEntity.getPilotId())); //NOSONAR

		return flightResponse;
	}

	@Override
	@Transactional
	public FlightResponse updateFlight(long id, Flight flight) {

		log.info("updating flight " + id);
		
		FlightEntity flightEntity = flightRepository.findById(id)
				.orElseThrow(() -> new FlightNotFoundException("604", "Pilot not found in the system for this id " + id));

		/* map flight input object to flight entity object automatically */
		flightEntity = modelMapper.map(flight, FlightEntity.class);

		flightEntity.setNumber(id);

		flightEntity = flightRepository.save(flightEntity);

		FlightResponse flightResponse = new FlightResponse(flightEntity);

		/*
		 * lets consider downstream airport-pilot-service is slow or down therefore
		 * calling airport-pilot-service using our defined circuit breaker
		 */
		flightResponse.setPilotResponse(pilotServiceCircuitBreaker.getPilotById(flightEntity.getPilotId()));

		log.info("updated flight " + id);

		return flightResponse;
	}

	@Override
	@Transactional
	public void deleteFlight(long id) {
		log.info("deleting flight " + id);
		flightRepository.deleteById(id);
	}

	@Override
	public Long searchFlightBySourceDestinationAndDate(String source, String destination, String departureDate) {
		log.info("searching flight by source, destination and departureDate");
		FlightEntity flightEntity = flightRepository.searchFlightBySourceDestinationAndDate(source, destination,
				departureDate);
		return flightEntity.getNumber();
	}
}