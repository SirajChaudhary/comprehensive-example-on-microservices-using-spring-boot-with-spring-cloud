package com.airport.checkin;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airport.checkin.controller.CheckinController;

@SpringBootTest
class AirportCheckinServiceApplicationTests {

	@Autowired
	private CheckinController checkinController;

	@Test
	void contextLoads() throws Exception {
		assertThat(checkinController).isNotNull();
	}
}