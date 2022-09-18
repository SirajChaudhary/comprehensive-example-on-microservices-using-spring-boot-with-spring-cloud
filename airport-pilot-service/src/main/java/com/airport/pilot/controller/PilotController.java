/**
  * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airport.pilot.request.Pilot;
import com.airport.pilot.response.PilotResponse;
import com.airport.pilot.service.PilotService;

/* You can find all CRUD APIs and findByXXX API in this Rest Controller */

@RestController
public class PilotController {

	@Autowired
	PilotService pilotService;

	/*
	 * A POST API to create a new pilot
	 * 
	 * @param Pilot (name, designation, experience)
	 * 
	 * @return PilotResponse (id, name, designation, experience)
	 */
	@PostMapping("/api/v1/pilots")
	public ResponseEntity<PilotResponse> createPilot(@Valid @RequestBody Pilot pilot) {
		PilotResponse pilotResponse = pilotService.createPilot(pilot);
		return new ResponseEntity<>(pilotResponse, HttpStatus.CREATED);
	}

	/*
	 * A GET API to retrieve an existing pilot
	 * 
	 * @param pilot id
	 * 
	 * @return PilotResponse (id, name, designation, experience)
	 */
	@GetMapping("/api/v1/pilots/{id}")
	public ResponseEntity<PilotResponse> getPilotById(@PathVariable Long id) {
		PilotResponse pilotResponse = pilotService.getPilotById(id);
		return new ResponseEntity<>(pilotResponse, HttpStatus.OK);
	}

	/*
	 * A GET API to retrieve all existing pilots
	 * 
	 * @return List<PilotResponse (id, name, designation, experience)>
	 */
	@GetMapping("/api/v1/pilots")
	public ResponseEntity<List<PilotResponse>> getAllPilots() {
		List<PilotResponse> listPilotResponse = pilotService.getAllPilots();
		return new ResponseEntity<>(listPilotResponse, HttpStatus.OK);
	}

	/*
	 * A PUT API to update an existing pilot
	 * 
	 * @param Pilot (name, designation, experience)
	 * 
	 * @param pilot id
	 * 
	 * @return PilotResponse (id, name, designation, experience)
	 */
	@PutMapping("/api/v1/pilots/{id}")
	public ResponseEntity<PilotResponse> updatePilot(@Valid @RequestBody Pilot pilot, @PathVariable Long id) {
		PilotResponse pilotResponse = pilotService.updatePilot(pilot, id);
		return new ResponseEntity<>(pilotResponse, HttpStatus.CREATED);
	}

	/*
	 * A DELETE API to delete an existing pilot
	 * 
	 * @param pilot id
	 */
	@DeleteMapping("/api/v1/pilots/{id}")
	public ResponseEntity<Void> deletePilot(@PathVariable Long id) {
		pilotService.deletePilot(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	/*
	 * A GET API to find existing pilots by designation
	 * 
	 * @param pilot designation
	 * 
	 * @return List<PilotResponse (id, name, designation, experience)>
	 */
	@GetMapping("/api/v1/pilots/find-by-designation")
	public ResponseEntity<List<PilotResponse>> findPilotsByDesignation(@RequestParam String designation) {
		List<PilotResponse> listPilotResponse = pilotService.findPilotsByDesignation(designation);
		return new ResponseEntity<>(listPilotResponse, HttpStatus.FOUND);
	}

	/*
	 * An API to flush the cache memory
	 */
	@DeleteMapping("/api/v1/pilots/flush-cache")
	public ResponseEntity<Void> flushCache() {
		pilotService.flushCache();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
