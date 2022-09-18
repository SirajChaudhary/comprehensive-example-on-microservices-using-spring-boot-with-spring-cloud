/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.service;

import com.airport.flight.request.Flight;
import com.airport.flight.response.FlightResponse;

public interface FlightService {

	public FlightResponse createFlight(Flight flight);

	public FlightResponse getFlightById(long id);

	public FlightResponse updateFlight(long id, Flight flightRequest);

	public void deleteFlight(long id);

	public Long searchFlightBySourceDestinationAndDate(String source, String destination, String departureDate);
}