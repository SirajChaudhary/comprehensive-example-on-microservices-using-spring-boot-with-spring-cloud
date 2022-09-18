package com.airport.flight;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airport.flight.controller.FlightController;

@SpringBootTest
class AirportFlightServiceApplicationTests {

	@Autowired
	private FlightController flightController;

	@Test
	void contextLoads() throws Exception {
		assertThat(flightController).isNotNull();
	}
}