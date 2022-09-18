package com.airport.flight.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightNotFoundException extends RuntimeException  {

	private String errorCode; // NOSONAR
	private String errorMessage; // NOSONAR
}
