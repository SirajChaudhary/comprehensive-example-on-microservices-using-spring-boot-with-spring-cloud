/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.response;

import com.airport.bookings.entity.BookingEntity;

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

	public BookingResponse(BookingEntity bookingEntity) {
		this.id = bookingEntity.getFlightNumber();
		this.fullname = bookingEntity.getFullname();
		this.mobile = bookingEntity.getMobile();
		this.email = bookingEntity.getEmail();
		this.address = bookingEntity.getAddress();
		this.pnrNumber = bookingEntity.getPnrNumber();
	}
}