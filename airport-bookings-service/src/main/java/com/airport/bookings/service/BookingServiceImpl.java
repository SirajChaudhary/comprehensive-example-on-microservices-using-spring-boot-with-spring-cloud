/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airport.bookings.email.BookingEmailService;
import com.airport.bookings.entity.BookingEntity;
import com.airport.bookings.feignclients.FlightFeignClient;
import com.airport.bookings.repository.BookingRepository;
import com.airport.bookings.request.Booking;
import com.airport.bookings.response.BookingResponse;
import com.airport.bookings.util.GeneratePNRNumber;

//import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	BookingEmailService bookingEmailService;

	@Autowired
	GeneratePNRNumber generatePNRNumber;

	@Autowired
	FlightFeignClient flightFeignClient;

	@Override
	@Transactional
	public BookingResponse createBooking(Booking booking) {
		log.info("starting bookingTicket() service method");

		/* map booking input object to booking entity object automatically */
		BookingEntity bookingEntity = modelMapper.map(booking, BookingEntity.class);

		/* generate and save a random PNR number */
		bookingEntity.setPnrNumber(generatePNRNumber.gePNRNumber());

		bookingEntity = bookingRepository.save(bookingEntity);

		BookingResponse bookingResponse = new BookingResponse(bookingEntity);

		/* calling the airport-flight-service using feign client */
		bookingResponse.setFlightResponse(flightFeignClient.getFlightById(bookingEntity.getFlightNumber()).getBody());

		/* email the booking information to the passenger */
		// bookingEmailService.sendEmail(bookingResponse.getEmail(), "Flight Booking Confirmed", bookingResponse);

		return bookingResponse;
	}

	@Override
	public BookingResponse getBookingById(long id) {
		log.info("starting getBookingByBookingId() service method");

		Optional<BookingEntity> optionalBookingEntity = bookingRepository.findById(id);

		if(optionalBookingEntity.isEmpty()) {
			// throw new NotFoundException(null);
		}
		
		BookingEntity bookingEntity = optionalBookingEntity.get();

		BookingResponse bookingResponse = new BookingResponse(bookingEntity);

		/* calling the airport-flight-service using feign client */
		bookingResponse.setFlightResponse(flightFeignClient.getFlightById(bookingEntity.getFlightNumber()).getBody());

		return bookingResponse;
	}

	@Override
	public BookingResponse findBookingByPNRNumber(String pnrNumber) {
		log.info("starting findBookingByPNRNumber() service method");
		BookingEntity bookingEntity = bookingRepository.findByPNRNumber(pnrNumber);
		BookingResponse bookingResponse = new BookingResponse(bookingEntity);

		/* calling the airport-flight-service using feign client */
		bookingResponse.setFlightResponse(flightFeignClient.getFlightById(bookingEntity.getFlightNumber()).getBody());

		return bookingResponse;
	}
}