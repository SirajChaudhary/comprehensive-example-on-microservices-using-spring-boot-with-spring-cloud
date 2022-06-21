/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/* Global Exception Handling */

@ControllerAdvice
public class SecurityControllerAdvicer extends ResponseEntityExceptionHandler {

	/* Custom Exception Handler */
	@ExceptionHandler(SecurityCustomException.class)
	public ResponseEntity<Map<String, Object>> handleSecurityCustomException(
			SecurityCustomException securityCustomException) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", securityCustomException.getErrorMessage());
		body.put("status", securityCustomException.getErrorCode());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}