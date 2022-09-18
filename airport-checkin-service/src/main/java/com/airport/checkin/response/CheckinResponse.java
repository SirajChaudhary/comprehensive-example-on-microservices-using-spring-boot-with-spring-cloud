/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.checkin.response;

import com.airport.checkin.entity.CheckinEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckinResponse {

	private long id;

	private String checkInBags;

	private String cabinBags;

	private long bookingId;

	public CheckinResponse(CheckinEntity checkinEntity) {
		this.id = checkinEntity.getId();
		this.checkInBags = checkinEntity.getCheckInBags();
		this.cabinBags = checkinEntity.getCabinBags();
		this.bookingId = checkinEntity.getBookingId();
	}
}