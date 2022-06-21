/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.service;

import com.airport.security.request.Security;
import com.airport.security.response.SecurityResponse;

public interface SecurityService {

	SecurityResponse createSecurity(Security security);

	SecurityResponse getSecurityById(Long id);

	SecurityResponse findSecurityByBookingId(Long bookingId);
}