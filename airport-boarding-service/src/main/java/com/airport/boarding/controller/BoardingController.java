/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.boarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.airport.boarding.response.BoardingResponse;
import com.airport.boarding.service.BoardingService;

@RestController
public class BoardingController {

	@Autowired
	BoardingService boardingService;

	/*
	 * API to find a boarding details of a booking by booking id
	 * 
	 * @param bookingId
	 * 
	 * @return BoardingResponse (id, CheckinResponse, CheckingResponse, BookingResponse)
	 */
	@GetMapping("/api/v1/boardings/find-by-booking-id/{bookingId}")
	public ResponseEntity<BoardingResponse> findBoardingByBookingId(@PathVariable Long bookingId) {
		BoardingResponse boardingResponse = boardingService.findBoardingByBookingId(bookingId);
		return new ResponseEntity<>(boardingResponse, HttpStatus.OK);
	}
}