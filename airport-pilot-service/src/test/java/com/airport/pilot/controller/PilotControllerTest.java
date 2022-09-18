package com.airport.pilot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.airport.pilot.AirportPilotServiceApplication;
import com.airport.pilot.request.Pilot;
import com.airport.pilot.service.PilotService;
import com.fasterxml.jackson.databind.ObjectMapper;

/* following imports to import methods such as status() and more */
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/* 
 * @WebMvcTest annotation can be used for a Spring MVC test that focuses <strong>only</strong> on Spring MVC components.
 * 
 * We have plan to test only PilotController (Only isolated component and NOT the other layers such as service, repository)
 * 
 * The PilotController is dependent on PilotService & PilotRepository So to test PilotController we need to mock the PilotService & PilotRepository objects 
 * 
 * Mocking is nothing but creating a dummy object 
 * 
 * @MockBean annotation is used to create a mock object
 * 
 * MockMvc is used to send a request for our RestController
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AirportPilotServiceApplication.class })
@WebMvcTest(value = PilotController.class)
class PilotControllerTest {

	@MockBean
	PilotService pilotService;

	@Autowired
	private MockMvc mockMvc;

	/* Testing the @RestController POST method */

	@Test
	void testCreatePilot() throws Exception {

		/*
		 * mocking service layer for any input request and output response because we
		 * are NOT testing service layer.
		 */
		when(pilotService.createPilot(ArgumentMatchers.any())).thenReturn(ArgumentMatchers.any());

		/* dummy request object */
		Pilot pilot = new Pilot("Sandeep Kumar", "Sr. Pilot", "12");

		/* convert object to json */
		String pilotJSON = new ObjectMapper().writeValueAsString(pilot);

		/* calling api /api/v1/pilot */

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/pilots")
				.contentType(MediaType.APPLICATION_JSON).content(pilotJSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(201, status);
	}

	/* Testing the @RestController GET method */

	@Test
	void testGetPilotById() throws Exception {

		when(pilotService.getPilotById(ArgumentMatchers.any())).thenReturn(ArgumentMatchers.any());

		/* dummy request pilot id */
		long pilotId = 1;

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/pilots/" + pilotId);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(200, status);
	}

	@Test
	void testGetAllPilots() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/pilots");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(200, status);
	}

	/* Testing the @RestController PUT method */

	@Test
	void testUpdatePilot() throws Exception {

		// when(pilotService.updatePilotById(ArgumentMatchers.any(),
		// ArgumentMatchers.any())).thenReturn(ArgumentMatchers.any());

		long pilotId = 1;

		Pilot pilot = new Pilot("Sandeep Kumar", "Sr. Captain", "18");

		String pilotJSON = new ObjectMapper().writeValueAsString(pilot);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/pilots/" + pilotId)
				.contentType(MediaType.APPLICATION_JSON).content(pilotJSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(201, status);
	}

	/* Testing the @RestController DELETE method */

	@Test
	void testDeletePilot() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/v1/pilots/{id}", "2")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}

	@Test
	void testFindPilotsByDesignation() throws Exception {

		when(pilotService.findPilotsByDesignation(ArgumentMatchers.any())).thenReturn(ArgumentMatchers.any());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/v1/pilots/find-by-designation?designation='Sr. Captain'");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(302, status);
	}
}
