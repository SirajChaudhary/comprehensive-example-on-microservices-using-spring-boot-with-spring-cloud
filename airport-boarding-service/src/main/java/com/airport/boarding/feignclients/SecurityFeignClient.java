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

import com.airport.boarding.response.SecurityResponse;

/* airport-security-service is the path as per the eureka server console */

@FeignClient(value = "api-gateway/airport-security-service")
public interface SecurityFeignClient {

	@GetMapping("/api/v1/securities/find-by-booking-id/{bookingId}")
	public ResponseEntity<SecurityResponse> findSecurityByBookingId(@PathVariable Long bookingId);
}