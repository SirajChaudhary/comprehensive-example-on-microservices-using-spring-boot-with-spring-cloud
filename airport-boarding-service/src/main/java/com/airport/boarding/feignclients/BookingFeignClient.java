/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.boarding.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.airport.boarding.response.BookingResponse;

/* airport-booking-service is the path as per the eureka server console */

@FeignClient(value = "api-gateway/airport-booking-service")
public interface BookingFeignClient {

	@GetMapping("/api/v1/bookings/{bookingId}")
	public ResponseEntity<BookingResponse> getBookingByBookingId(@PathVariable long bookingId);
}