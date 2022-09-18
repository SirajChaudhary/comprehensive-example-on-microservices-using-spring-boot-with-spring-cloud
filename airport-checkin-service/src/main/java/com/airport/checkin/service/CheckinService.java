/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.checkin.service;

import com.airport.checkin.request.Checkin;
import com.airport.checkin.response.CheckinResponse;

public interface CheckinService {

	CheckinResponse createCheckin(Checkin checkin);
	CheckinResponse getCheckinById(Long id);
	CheckinResponse findCheckinByBookingId(Long bookingId);
}