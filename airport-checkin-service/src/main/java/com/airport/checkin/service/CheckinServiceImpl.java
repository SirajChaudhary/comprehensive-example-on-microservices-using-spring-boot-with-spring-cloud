/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.checkin.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airport.checkin.entity.CheckinEntity;
import com.airport.checkin.repository.CheckinRepository;
import com.airport.checkin.request.Checkin;
import com.airport.checkin.response.CheckinResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CheckinServiceImpl implements CheckinService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	CheckinRepository checkinRepository;

	@Override
	@Transactional
	public CheckinResponse createCheckin(Checkin checkin) {

		/* log is static variable declared in @Slf4j */
		log.info("saving check-in details of bookign id " + checkin.getBookingId());

		/* map check-in input object to check-in entity object automatically */
		CheckinEntity checkinEntity = modelMapper.map(checkin, CheckinEntity.class);

		checkinRepository.save(checkinEntity);

		log.info("saved check-in details of booking id " + checkin.getBookingId());

		return new CheckinResponse(checkinEntity);
	}

	@Override
	public CheckinResponse getCheckinById(Long id) {
		log.info("fetching check-in by id " + id);
		CheckinEntity checkinEntity = checkinRepository.findById(id).get();
		return new CheckinResponse(checkinEntity);
	}

	@Override
	public CheckinResponse findCheckinByBookingId(Long bookingId) {
		log.info("fetching check-in by booking id " + bookingId);
		CheckinEntity checkinEntity = checkinRepository.findByBookingId(bookingId).get();
		return new CheckinResponse(checkinEntity);
	}
}