/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.service;

import com.airport.bookings.request.Booking;
import com.airport.bookings.response.BookingResponse;

public interface BookingService {

	BookingResponse createBooking(Booking booking);

	BookingResponse getBookingById(long id);

	BookingResponse findBookingByPNRNumber(String pnrNumber);
}