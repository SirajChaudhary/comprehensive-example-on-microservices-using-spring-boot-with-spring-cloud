/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airport.flight.entity.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

	@Query("SELECT f FROM FlightEntity f WHERE f.source = ?1 and f.destination = ?2 and f.departureDate = ?3")
	FlightEntity searchFlightBySourceDestinationAndDate(String source, String destination, String departureDate);
}