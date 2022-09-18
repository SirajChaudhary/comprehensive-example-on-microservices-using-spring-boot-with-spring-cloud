/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport.flight.request.Flight;
import com.airport.flight.response.FlightResponse;
import com.airport.flight.service.FlightService;

@RestController
public class FlightController {

	@Autowired
	FlightService flightService;

	/*
	 * A POST API to create a new flight
	 * 
	 * @param Flight (id, vendor, source, destination, departureDate, departureTime, pilotId(FK))
	 * 
	 * @return FlightResponse (number, vendor, source, destination, departureDate,
	 * departureTime, PilotResponse(id, name, designation, experience)) 
	 * This output object FlightResponse also includes respective pilot details (PilotResponse)
	 */
	@PostMapping("/api/v1/flights")
	public ResponseEntity<FlightResponse> createFlight(@RequestBody Flight flight) {
		FlightResponse flightResponse = flightService.createFlight(flight);
		return new ResponseEntity<>(flightResponse, HttpStatus.CREATED);
	}

	/*
	 * A GET API to retrieve an existing flight
	 * 
	 * @param flight id
	 * 
	 * @return FlightResponse (number, vendor, source, destination, departureDate,
	 * departureTime, PilotResponse(id, name, designation, experience))
	 */
	@GetMapping("/api/v1/flights/{id}")
	public ResponseEntity<FlightResponse> getFlightById(@PathVariable long id) {
		FlightResponse flightResponse = flightService.getFlightById(id);
		return new ResponseEntity<>(flightResponse, HttpStatus.OK);
	}

	/*
	 * A PUT API to update an existing flight
	 * 
	 * @param flight id
	 * 
	 * @param Flight (id, vendor, source, destination, departureDate, departureTime,
	 * pilotId(FK))
	 * 
	 * @return FlightResponse (number, vendor, source, destination, departureDate,
	 * departureTime, PilotResponse(id, name, designation, experience))
	 */
	@PutMapping("/api/v1/flights/{id}")
	public ResponseEntity<FlightResponse> updateFlight(@PathVariable long id, @RequestBody Flight flight) {
		FlightResponse flightResponse = flightService.updateFlight(id, flight);
		return new ResponseEntity<>(flightResponse, HttpStatus.CREATED);
	}

	/*
	 * A DELETE API to delete an existing flight
	 * 
	 * @param flight id
	 */
	@DeleteMapping("/api/v1/flights/{id}")
	public ResponseEntity<Void> deleteFlight(@PathVariable long id) {
		flightService.deleteFlight(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	/*
	 * A GET API to search an existing flight by source, destination and departureDate
	 * 
	 * @param source, destination, departureDate
	 * 
	 * @return flight id
	 */
	@GetMapping("/api/v1/flights/search")
	public ResponseEntity<Map<String, Object>> searchFlightBySourceDestinationAndDate(@RequestParam String source,
			@RequestParam String destination, @RequestParam String departureDate) {
		Long flightId = flightService.searchFlightBySourceDestinationAndDate(source, destination, departureDate);

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("flight id ", flightId);
		body.put("message ", "This flight is available for booking");

		return new ResponseEntity<>(body, HttpStatus.FOUND);
	}
}