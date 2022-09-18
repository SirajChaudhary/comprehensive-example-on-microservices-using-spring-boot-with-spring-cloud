package com.airport.api.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class APIGatewayFilter implements GlobalFilter {

	Logger logger = LoggerFactory.getLogger(APIGatewayFilter.class);

	/* API gateway pre-filter method for all API calls */

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		/* we log the request header (x-api-key) of all API calls */
		
		logger.info("x-api-key = {}", exchange.getRequest().getHeaders().getFirst("x-api-key"));

		/* once pre-filter is applied the call will be forwarded to appropriate microservice */

		return chain.filter(exchange);
	}
}