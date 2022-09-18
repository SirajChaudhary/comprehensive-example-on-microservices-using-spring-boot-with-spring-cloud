/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.response;

import com.airport.security.entity.SecurityEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SecurityResponse {

	private long id;

	private String identityProof;

	private String covidReport;

	private String ctScan;

	private String status;

	private long bookingId;

	public SecurityResponse(SecurityEntity securityEntity) {
		this.id = securityEntity.getId();
		this.identityProof = securityEntity.getIdentityProof();
		this.covidReport = securityEntity.getCovidReport();
		this.ctScan = securityEntity.getCtScan();
		this.status = securityEntity.getStatus();
		this.bookingId = securityEntity.getBookingId();
	}
}
