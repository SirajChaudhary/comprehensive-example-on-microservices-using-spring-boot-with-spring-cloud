/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "security")
@Getter
@Setter
public class SecurityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "identity_proof")
	private String identityProof;

	@Column(name = "covid_report")
	private String covidReport;

	@Column(name = "ct_scan")
	private String ctScan;

	@Column(name = "status")
	private String status;

	@Column(name = "booking_id")
	private Long bookingId;
}