/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Custom Exception */
/* You can have various fields and constructors in it */
@SuppressWarnings("serial")
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityCustomException extends RuntimeException {

	private String errorCode; //NOSONAR
	private String errorMessage; //NOSONAR
}