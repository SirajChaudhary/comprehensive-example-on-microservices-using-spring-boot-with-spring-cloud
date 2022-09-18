/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PilotResponse {

	private Long id;
	private String name;
	private String designation;
	private String experience;
}