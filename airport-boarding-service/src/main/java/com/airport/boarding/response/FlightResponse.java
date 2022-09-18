/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.boarding.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
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

}