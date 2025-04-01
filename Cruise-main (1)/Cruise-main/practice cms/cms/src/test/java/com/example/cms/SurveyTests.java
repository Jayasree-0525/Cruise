package com.example.cms;

import com.example.cms.model.repository.SurveyRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


// Mar 31
import com.example.cms.model.entity.Survey;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.cms.model.entity.Cruise;
import com.example.cms.model.entity.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SurveyTests {


	@Autowired
	private MockMvc mockMvc;


	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SurveyRepository surveyRepository;

	@Test
	void deleteSurveys() throws Exception{

		Customer customer = new Customer();
		customer.setId(6);

		Cruise cruise = new Cruise();
		cruise.setCruiseId(2);

		Survey s = new Survey();
		s.setSurveyId(2);
		s.setCustomer(customer);
		s.setCruise(cruise);
		s.setDateOfSurvey("Mar 25, 2025");
		surveyRepository.save(s);

		MockHttpServletResponse response = mockMvc.perform(
						delete("/surveys/2").
								contentType("application/json"))
				.andReturn().getResponse();


		assertEquals(200, response.getStatus());
		assertTrue(surveyRepository.findById(2).isEmpty());
	}

	@Test
	void addSurveys() throws Exception{

		ObjectNode surveyJson = objectMapper.createObjectNode();
		surveyJson.put("surveyId", 8888);
		surveyJson.put("customerId", 6);
		surveyJson.put("cruiseId", 1);
		surveyJson.put("dateOfSurvey", "March 25, 2025");


		MockHttpServletResponse response = mockMvc.perform(
						post("/surveys").
								contentType("application/json").
								content(surveyJson.toString()))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());


		assertTrue(surveyRepository.findById(8888).isPresent());
		Survey addedSurvey = surveyRepository.findById(8888).get();

		assertEquals(8888, addedSurvey.getSurveyId());
		assertEquals(6, addedSurvey.getCustomer().getId());
		assertEquals(1, addedSurvey.getCruise().getCruiseId());
		assertEquals("March 25, 2025", addedSurvey.getDateOfSurvey());

	}

	@Test
	void getSurveys() throws Exception{
		MockHttpServletResponse response = mockMvc.perform(get("/surveys/1"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());

		ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
		assertEquals(1, receivedJson.get("surveyId").intValue()); //survey 1 //added this check of the surveyId on Mar 31
		assertEquals(6, receivedJson.get("customer").get("id").intValue());//customer 6
		assertEquals(1, receivedJson.get("cruise").get("cruiseId").intValue()); //cruise 1
		assertEquals("March 25, 2025", receivedJson.get("dateOfSurvey").textValue());
	}

	@Test
	void updateSurveys() throws Exception {

		ObjectNode surveyJsonUpdated = objectMapper.createObjectNode();
		surveyJsonUpdated.put("surveyId", 1);
		surveyJsonUpdated.put("customerId", 6);
		surveyJsonUpdated.put("cruiseId", 1);
		surveyJsonUpdated.put("dateOfSurvey", "August 1, 2025");


		MockHttpServletResponse response2 = mockMvc.perform(
						//perform an update aka put instead of post
						put("/surveys/1").
								contentType("application/json").
								content(surveyJsonUpdated.toString()))
				.andReturn().getResponse();

		assertEquals(200, response2.getStatus());

		assertTrue(surveyRepository.findById(1).isPresent());
		Survey updatedSurvey = surveyRepository.findById(1).get();

		assertEquals(1, updatedSurvey.getSurveyId());
		assertEquals(6, updatedSurvey.getCustomer().getId());
		assertEquals(1, updatedSurvey.getCruise().getCruiseId());
		assertEquals("August 1, 2025", updatedSurvey.getDateOfSurvey());
	}
}



