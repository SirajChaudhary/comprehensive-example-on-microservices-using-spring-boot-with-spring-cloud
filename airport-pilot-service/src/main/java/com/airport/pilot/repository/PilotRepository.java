/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airport.pilot.entity.PilotEntity;

@Repository
public interface PilotRepository extends JpaRepository<PilotEntity, Long> {

	@Query("SELECT p FROM PilotEntity p WHERE p.designation = ?1")
	List<PilotEntity> findByDesignation(String designation);
}