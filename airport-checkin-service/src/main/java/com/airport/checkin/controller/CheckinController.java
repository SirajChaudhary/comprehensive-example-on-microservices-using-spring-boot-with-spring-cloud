/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.checkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airport.checkin.request.Checkin;
import com.airport.checkin.response.CheckinResponse;
import com.airport.checkin.service.CheckinService;

@RestController
public class CheckinController {

	@Autowired
	CheckinService checkinService;

	/*
	 * API to add check-in details
	 * 
	 * @param Checkin (checkInBags, checkInBags, bookingId)
	 * 
	 * @return CheckinResponse (id, checkInBags, checkInBags, bookingId)
	 */
	@PostMapping("/api/v1/checkin")
	public ResponseEntity<CheckinResponse> createCheckin(@RequestBody Checkin checkin) {
		CheckinResponse checkinResponse = checkinService.createCheckin(checkin);
		return new ResponseEntity<>(checkinResponse, HttpStatus.CREATED);
	}

	/*
	 * API to get a check-in details
	 * 
	 * @param id is the checkin id
	 * 
	 * @return CheckinResponse (id, checkInBags, checkInBags, bookingId)
	 */
	@GetMapping("/api/v1/checkin/{id}")
	public ResponseEntity<CheckinResponse> getCheckinById(@PathVariable Long id) {
		CheckinResponse checkinResponse = checkinService.getCheckinById(id);
		return new ResponseEntity<>(checkinResponse, HttpStatus.OK);
	}

	/*
	 * API to find a checkin details by booking id
	 * 
	 * @param bookingId
	 * 
	 * @return CheckinResponse (id, checkInBags, checkInBags, bookingId)
	 */
	@GetMapping("/api/v1/checkin/find-by-booking-id/{bookingId}")
	public ResponseEntity<CheckinResponse> findCheckinByBookingId(@PathVariable Long bookingId) {
		CheckinResponse checkinResponse = checkinService.findCheckinByBookingId(bookingId);
		return new ResponseEntity<>(checkinResponse, HttpStatus.OK);
	}
}