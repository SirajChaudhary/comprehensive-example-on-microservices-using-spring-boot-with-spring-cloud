/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.response;

import java.io.Serializable;

import com.airport.pilot.entity.PilotEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PilotResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String designation;
	private String experience;

	public PilotResponse(PilotEntity pilotEntity) {
		this.id = pilotEntity.getId();
		this.name = pilotEntity.getName();
		this.designation = pilotEntity.getDesignation();
		this.experience = pilotEntity.getExperience();
	}
}