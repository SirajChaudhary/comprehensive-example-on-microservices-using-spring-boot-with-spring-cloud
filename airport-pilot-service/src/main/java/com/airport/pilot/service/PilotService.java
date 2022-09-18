/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.service;

import java.util.List;

import com.airport.pilot.request.Pilot;
import com.airport.pilot.response.PilotResponse;

public interface PilotService {

	public PilotResponse createPilot(Pilot pilot);

	public PilotResponse getPilotById(Long id);

	public List<PilotResponse> getAllPilots();

	public PilotResponse updatePilot(Pilot pilot, Long id);

	public void deletePilot(Long id);

	public List<PilotResponse> findPilotsByDesignation(String designation);

	public void flushCache();
}