package com.airport.boarding;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airport.boarding.controller.BoardingController;

@SpringBootTest
class AirportBoardingServiceApplicationTests {

	@Autowired
	private BoardingController boardingController;

	@Test
	void contextLoads() throws Exception {
		assertThat(boardingController).isNotNull();
	}
}