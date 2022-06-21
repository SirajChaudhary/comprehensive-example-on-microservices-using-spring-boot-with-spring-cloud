package com.airport.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airport.security.controller.SecurityController;

@SpringBootTest
class AirportSecurityServiceApplicationTests {

	@Autowired
	private SecurityController securityController;

	@Test
	void contextLoads() throws Exception {
		assertThat(securityController).isNotNull();
	}

}
