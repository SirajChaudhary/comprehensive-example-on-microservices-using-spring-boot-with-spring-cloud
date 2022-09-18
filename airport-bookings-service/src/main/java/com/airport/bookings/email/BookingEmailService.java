/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.airport.bookings.response.BookingResponse;

@Component
public class BookingEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String sendTo, String subject, BookingResponse bookingResponse) {

		String message = "Dear " + bookingResponse.getFullname() + ", \n\n" + "Your flight booking is confirmed. \n\n "
				+ "BOOKING NUMBER = " + bookingResponse.getId() + " \n " + "PNR NUMBER = "
				+ bookingResponse.getPnrNumber() + " \n " + "SOURCE = "
				+ bookingResponse.getFlightResponse().getSource() + " \n " + "DESTINATION = "
				+ bookingResponse.getFlightResponse().getDestination() + " \n " + "DEPARTURE DATE = "
				+ bookingResponse.getFlightResponse().getDepartureDate() + " \n " + "DEPARTURE TIME = "
				+ bookingResponse.getFlightResponse().getDepartureTime() + " \n\n" + "Thank you for choosing "
				+ bookingResponse.getFlightResponse().getVendor();

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setTo(sendTo);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);

		javaMailSender.send(simpleMailMessage);

	}
}
