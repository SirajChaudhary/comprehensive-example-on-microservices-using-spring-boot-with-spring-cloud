/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.security.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airport.security.entity.SecurityEntity;
import com.airport.security.exception.SecurityCustomException;
import com.airport.security.repository.SecurityRepository;
import com.airport.security.request.Security;
import com.airport.security.response.SecurityResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class SecurityServiceImpl implements SecurityService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	SecurityRepository securityRepository;

	@Override
	@Transactional
	public SecurityResponse createSecurity(Security security) {

		/* throw a custom exception on a condition */
		/*
		 * we choose 6xx series for custom exception as 1xx, 2xx, 3xx, 4xx are reserved
		 */
		if (security.getCovidReport().equals("positive") || security.getCovidReport().equals("Not Available"))
			throw new SecurityCustomException("600",
					"You are not allowed! Either your covid report is +ve or you did not do covid testing.");

		log.info("saving security check id " + security.getBookingId());

		/* map security input object to security entity object automatically */
		SecurityEntity securityEntity = modelMapper.map(security, SecurityEntity.class);

		securityRepository.save(securityEntity);

		log.info("saved security check id " + security.getBookingId());

		return new SecurityResponse(securityEntity);
	}

	@Override
	public SecurityResponse getSecurityById(Long id) {
		log.info("fetching security by security id " + id);
		SecurityEntity securityEntity = securityRepository.findById(id).get();
		return new SecurityResponse(securityEntity);
	}

	@Override
	public SecurityResponse findSecurityByBookingId(Long bookingId) {
		log.info("fetching security by booking id " + bookingId);
		SecurityEntity securityEntity = securityRepository.findByBookingId(bookingId).get();
		return new SecurityResponse(securityEntity);
	}
}