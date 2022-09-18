/**
 * @author SIRAJ CHAUDHARY
 * 
 * https://github.com/SirajChaudhary
 * 
 * https://www.linkedin.com/in/sirajchaudhary/
 */

package com.airport.pilot.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airport.pilot.entity.PilotEntity;
import com.airport.pilot.exception.PilotCustomException;
import com.airport.pilot.repository.PilotRepository;
import com.airport.pilot.request.Pilot;
import com.airport.pilot.response.PilotResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CacheConfig(cacheNames = { "airport-pilot-service-cache" })
@Transactional(readOnly = true)
public class PilotServiceImpl implements PilotService {

	@Autowired
	PilotRepository pilotRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional
	public PilotResponse createPilot(Pilot pilot) {

		/*
		 * we throw a custom exception on a condition
		 * 
		 * we choose 6xx series for custom exception status code because 1xx, 2xx, 3xx,
		 * 4xx are reserved
		 */
		if (pilot.getName().isEmpty() || pilot.getName().length() == 0)
			throw new PilotCustomException("600", "Please provide pilot name. Its blank!");

		log.info("adding a new pilot " + pilot.getName());

		/* map pilot value-object to pilot entity-object automatically */
		PilotEntity pilotEntity = modelMapper.map(pilot, PilotEntity.class);

		pilotRepository.save(pilotEntity);

		log.info("added a new pilot " + pilot.getName());

		return new PilotResponse(pilotEntity);
	}

	/* if data is in cache it'll be taken else method will be executed & hit DB. */

	@Override
	@Cacheable
	public PilotResponse getPilotById(Long id) {

		log.info("retrieving an existing pilot " + id);

		PilotEntity pilotEntity = pilotRepository.findById(id)
				.orElseThrow(() -> new PilotCustomException("604", "Pilot not found in the system for this id " + id));

		log.info("retrieved an existing pilot " + id);

		return new PilotResponse(pilotEntity);
	}

	@Override
	public List<PilotResponse> getAllPilots() {

		log.info("retrieving all existing pilots");

		List<PilotEntity> listPilotEntity = pilotRepository.findAll();

		List<PilotResponse> listPilotResponse = new ArrayList<>();

		for (PilotEntity pilotEntity : listPilotEntity)
			listPilotResponse.add(new PilotResponse(pilotEntity));

		log.info("retrieved all existing pilots");

		return listPilotResponse;
	}

	@Override
	@CachePut(key = "#id")
	@Transactional
	public PilotResponse updatePilot(Pilot pilot, Long id) {

		log.info("updating an existing pilot " + id);

		PilotEntity pilotEntity = pilotRepository.findById(id)
				.orElseThrow(() -> new PilotCustomException("604", "Pilot not found in the system for this id " + id));

		/* map pilot value-object to pilot entity-object automatically */
		pilotEntity = modelMapper.map(pilot, PilotEntity.class);

		pilotEntity.setId(id);

		pilotRepository.save(pilotEntity);

		log.info("updated an existing pilot " + id);

		return new PilotResponse(pilotEntity);
	}

	@Override
	@CacheEvict
	@Transactional
	public void deletePilot(Long id) {

		log.info("deleting an existing pilot " + id);

		pilotRepository.deleteById(id);

		log.info("deleted an existing pilot " + id);
	}

	/* we have added a cache condition */

	@Override
	@Cacheable(condition = "#designation=='Sr. Captain'")
	public List<PilotResponse> findPilotsByDesignation(String designation) {

		log.info("finding pilots by designation " + designation);

		List<PilotEntity> listPilotEntity = pilotRepository.findByDesignation(designation);

		if (listPilotEntity.isEmpty())
			throw new PilotCustomException("604",
					"Pilot not found in the system for this designation '" + designation + "'");

		List<PilotResponse> listPilotResponse = new ArrayList<>();

		for (PilotEntity pilotEntity : listPilotEntity) {
			PilotResponse pilotResponse = modelMapper.map(pilotEntity, PilotResponse.class);
			listPilotResponse.add(pilotResponse);
		}

		log.info("found pilots by designation " + designation);

		return listPilotResponse;
	}

	/* it remove all entries from the cache airport-pilot-service-cache */
	@Override
	@CacheEvict(allEntries = true)
	public void flushCache() {
	}
}