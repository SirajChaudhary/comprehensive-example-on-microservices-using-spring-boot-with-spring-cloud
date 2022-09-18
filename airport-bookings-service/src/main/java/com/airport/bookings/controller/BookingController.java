/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport.bookings.request.Booking;
import com.airport.bookings.response.BookingResponse;
import com.airport.bookings.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;

	/*
	 * API to book a flight ticket
	 * 
	 * @param Booking (fullname, mobile, email, address, flightNumber(FK))
	 * 
	 * @return BookingResponse (id, mobile, email, address, FlightResponse object)
	 */
	@PostMapping("/api/v1/bookings")
	public ResponseEntity<BookingResponse> createBooking(@RequestBody Booking booking) {
		BookingResponse bookingResponse = bookingService.createBooking(booking);
		return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
	}

	/*
	 * API to fetch a booking details by bookingId
	 * 
	 * @param bookingId
	 * 
	 * @return BookingResponse (id, mobile, email, address, FlightResponse object)
	 */
	@GetMapping("/api/v1/bookings/{id}")
	public ResponseEntity<BookingResponse> getBookingById(@PathVariable long id) {
		BookingResponse bookingResponse = bookingService.getBookingById(id);
		return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
	}

	/*
	 * API to fetch a booking details by pnrNumber
	 * 
	 * @param pnrNumber
	 * 
	 * @return BookingResponse (id, mobile, email, address, FlightResponse object)
	 */
	@GetMapping("/api/v1/bookings/find-by-pnr")
	public ResponseEntity<BookingResponse> findBookingByPNRNumber(@RequestParam String pnrNumber) {
		BookingResponse bookingResponse = bookingService.findBookingByPNRNumber(pnrNumber);
		return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
	}
}