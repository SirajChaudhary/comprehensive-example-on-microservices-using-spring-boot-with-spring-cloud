/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.boarding.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingResponse {

	private long id;

	private String fullname;

	private String mobile;

	private String email;

	private String address;

	private String pnrNumber;

	private FlightResponse flightResponse;
}