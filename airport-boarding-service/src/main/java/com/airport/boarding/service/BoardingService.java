/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.boarding.service;

import com.airport.boarding.response.BoardingResponse;

public interface BoardingService {
	BoardingResponse findBoardingByBookingId(Long bookingId);
}