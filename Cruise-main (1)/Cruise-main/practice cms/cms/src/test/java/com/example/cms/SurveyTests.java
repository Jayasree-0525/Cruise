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

	//reference: student test
	@Test
	void getSurvey() throws Exception{
		MockHttpServletResponse response = mockMvc.perform(get("/surveys/1111"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());

		//INSERT INTO survey(cruiseId, customerId, questionId, qualitativeAnswer, quantitativeAnswer);

		ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
		assertEquals(6L, receivedJson.get("cruiseId").longValue()); //cruise 6
		assertEquals(1111L, receivedJson.get("customerId").longValue()); //customer 1111
		assertEquals(1L, receivedJson.get("questionId").longValue()); //question 1
		assertEquals(null, receivedJson.get("qualitative answer").textValue()); //no qualitative answer for this q
		assertEquals(10, receivedJson.get("quantitative answer").textValue()); //rating of 10/10
	}

	//from student tests:
	/*
	@Test
	void addStudent() throws Exception{

		ObjectNode studentJson = objectMapper.createObjectNode();
		studentJson.put("id", 8888L);
		studentJson.put("firstName", "first");
		studentJson.put("lastName", "last");
		studentJson.put("email", "first@last.com");

		MockHttpServletResponse response = mockMvc.perform(
				post("/students").
						contentType("application/json").
						content(studentJson.toString()))
				.andReturn().getResponse();

		// assert HTTP code of response is 200 OK
		assertEquals(200, response.getStatus());

		// Assert student with id 8888 exists in our repository and then get the student object
		assertTrue(studentRepository.findById(8888L).isPresent());
		Student addedStudent = studentRepository.findById(8888L).get();

		// Assert the details of the students are correct
		assertEquals(8888L, addedStudent.getId());
		assertEquals("first", addedStudent.getFirstName());
		assertEquals("last", addedStudent.getLastName());
		assertEquals("first@last.com", addedStudent.getEmail());
	}
	*/

	@Test
	void deleteSurvey() throws Exception{

		//INSERT INTO survey(cruiseId, customerId, questionId, qualitativeAnswer, quantitativeAnswer);

		//make a fake survey
		Survey s = new Survey();
		s.setCruiseId(6L); //cruise 6
		s.setCustomerId(1111L); //customer 1111
		s.setQuestionId(1L); //question 1
		s.setQualitativeAnswer("The whole cruise was great!");
		s.setQuantitativeAnswer(null);
		surveyRepository.save(s);

		//do a mock delete
		MockHttpServletResponse response = mockMvc.perform(
				delete("/surveys/6").
						contentType("application/json"))
				.andReturn().getResponse();

		assertEquals(200, response.getStatus());
		assertTrue(surveyRepository.findById(6L).isEmpty());
	}


}
