/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.bookings.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class GeneratePNRNumber {

	public String gePNRNumber() {
		String pnrNumber = usingUUID();
		return pnrNumber.substring(0, 6);
	}

	static String usingUUID() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().replace("-", "");
	}
}