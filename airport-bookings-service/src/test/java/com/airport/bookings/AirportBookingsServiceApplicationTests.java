package com.airport.bookings;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airport.bookings.controller.BookingController;

@SpringBootTest
class AirportBookingsServiceApplicationTests {

	@Autowired
	private BookingController bookingController;

	@Test
	void contextLoads() throws Exception {
		assertThat(bookingController).isNotNull();
	}
}