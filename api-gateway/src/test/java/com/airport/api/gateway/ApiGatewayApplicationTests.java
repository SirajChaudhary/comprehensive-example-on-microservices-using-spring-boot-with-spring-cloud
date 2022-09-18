package com.airport.api.gateway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airport.api.gateway.filter.APIGatewayFilter;

@SpringBootTest
class ApiGatewayApplicationTests {

	@Autowired
	APIGatewayFilter apiGatewayFilter;
	
	@Test
	void contextLoads() throws Exception {
		assertThat(apiGatewayFilter).isNotNull();
	}
}