/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.response;

import com.airport.flight.entity.FlightEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FlightResponse {

	private long number;

	private String vendor;

	private String source;

	private String destination;

	private String departureDate;

	private String departureTime;

	private String status;

	private PilotResponse pilotResponse;

	public FlightResponse(FlightEntity flightEntity) {
		this.number = flightEntity.getNumber();
		this.vendor = flightEntity.getVendor();
		this.source = flightEntity.getSource();
		this.destination = flightEntity.getDestination();
		this.departureDate = flightEntity.getDepartureDate();
		this.departureTime = flightEntity.getDepartureTime();
		this.status = flightEntity.getStatus();
	}
}