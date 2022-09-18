/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.checkin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airport.checkin.entity.CheckinEntity;

@Repository
public interface CheckinRepository extends JpaRepository<CheckinEntity, Long> {

	@Query("SELECT c FROM CheckinEntity c WHERE c.bookingId = ?1")
	Optional<CheckinEntity> findByBookingId(Long bookingId);
}