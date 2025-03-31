/*


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






   @Test
   void deleteSurvey() throws Exception{


      //INSERT INTO survey(cruiseId, customerId, questionId, qualitativeAnswer, quantitativeAnswer);


      //New version of data.sql (from just before Mar 31)
      //INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (1, 6, 1, 'March 25, 2025');


      //make a fake survey
      // Note: cruise Id (and cust and q Ids) are int's
      // and cruiseId is found in Person class
      Survey s = new Survey();
      s.setCruiseId(6); //cruise 6
      s.setCustomerId(1111); //customer 1111
      s.setQuestionId(1); //question 1
      s.setDateOfSurvey("March 25, 2025");
      surveyRepository.save(s);


      //do a mock delete
      MockHttpServletResponse response = mockMvc.perform(
            delete("/surveys/6").
                  contentType("application/json"))
            .andReturn().getResponse();


      assertEquals(200, response.getStatus());
      assertTrue(surveyRepository.findById(6).isEmpty());
   }




}
*/


// -------------------------------Mar 31, 2025 ----------------------------
// THINK NEED TO change all L's aka long's to int.s (if that attrib. is int)
// cruise, cust, and question all hav int id's


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
import com.example.cms.model.entity.Person;
import com.example.cms.model.entity.Question;


import lombok.Getter;
import lombok.Setter;

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


	//OLD GET from before Mar 30
   /*
   //reference: student test
   // GET
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


      //assertEquals(null, receivedJson.get("qualitative answer").textValue()); //no qualitative answer for this q
      //assertEquals(10, receivedJson.get("quantitative answer").textValue()); //rating of 10/10
   }
   */




	//reference: student test
	// GET
	@Test
	void getSurvey() throws Exception{
		MockHttpServletResponse response = mockMvc.perform(get("/surveys/1111"))
				.andReturn().getResponse();


		assertEquals(200, response.getStatus());

		// new as of start of Mar 31
		//INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (1, 6, 1, 'March 25, 2025');


		ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);

		assertEquals(1, receivedJson.get("surveyId")); //survey 1 //added this check of the surveyId on Mar 31
		assertEquals(6, receivedJson.get("customerId")); //customer 6
		assertEquals(1, receivedJson.get("cruiseId")); //cruise 1
		assertEquals("March 25, 2025", receivedJson.get("dateOfSurvey"));


		//assertEquals(null, receivedJson.get("qualitative answer").textValue()); //no qualitative answer for this q
		//assertEquals(10, receivedJson.get("quantitative answer").textValue()); //rating of 10/10
	}




	//reference: student tests

	//POST and PUT
	@Test
	void addSurvey() throws Exception{


		// part 1: POST (aka create a new thing)
		//---------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------


		ObjectNode surveyJson = objectMapper.createObjectNode();
		surveyJson.put("surveyId", 8888); //NOTE idk if this needs to be 1 bec the data.sql file entry had an id of 1
		surveyJson.put("customerId", 6);
		surveyJson.put("cruiseId", 1); // DO WE need to make a cruise JSON for the test case with Id of 1? same for customer id in line above
		surveyJson.put("dateOfSurvey", "March 25, 2025");


		MockHttpServletResponse response = mockMvc.perform(
						post("/surveys").
								contentType("application/json").
								content(surveyJson.toString()))
				.andReturn().getResponse();


		// assert HTTP code of response is 200 OK
		assertEquals(200, response.getStatus());


		// Assert survey with id 8888 exists in our repository and then get the survey object
		assertTrue(surveyRepository.findById(8888).isPresent());
		Survey addedSurvey = surveyRepository.findById(8888).get();

		// Mar 31 testing:

		// Assert the details of the surveys are correct
		assertEquals(8888, addedSurvey.getSurveyId());
		assertEquals(6, addedSurvey.getCustomer().getId()); //WORKING MAYB?
		assertEquals(1, addedSurvey.getCruise().getCruiseId()); //WORKING MAYB?
		assertEquals("March 25, 2025", addedSurvey.getDateOfSurvey());

		// Note: instead of doing this:
		//assertEquals(6, addedSurvey.getCustomer().getCustomerId());
		//we need to do .getId() bec cust Id is actually in Person and just called id

		// ver from before Mar 31:

		// Assert the details of the surveys are correct
		/*
		assertEquals(8888, addedSurvey.getSurveyId());
		assertEquals(6, addedSurvey.getCustomerId());
		assertEquals(1, addedSurvey.getCruiseId());
		assertEquals("March 25, 2025", addedSurvey.getDateOfSurvey());
		*/




		// part 2: PUT (aka update)
		//---------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------


		//make a new JSON so that in "insomnia" you would choose Update and submit this JSON, hoping to update the...
		//...already-existing survey with id of 8888
		//You are just updating the "date" attribute (compare to the Post case above) change from March 25 to Aug 1
		ObjectNode surveyJsonUpdated = objectMapper.createObjectNode();
		surveyJsonUpdated.put("surveyId", 8888); //NOTE idk if this needs to be 1 bec the data.sql file entry had an id of 1
		surveyJsonUpdated.put("customerId", 6);
		surveyJsonUpdated.put("cruiseId", 1); // DO WE need to make a cruise JSON for the test case with Id of 1? same for customer id in line above
		surveyJsonUpdated.put("dateOfSurvey", "August 1, 2025");


		//I called it response2 not response bec response was a var above already (in this func addSurvey())
		MockHttpServletResponse response2 = mockMvc.perform(
						//perform an update aka put instead of post
						put("/surveys").
								contentType("application/json").
								content(surveyJsonUpdated.toString()))
				.andReturn().getResponse();


		// assert HTTP code of response is 200 OK
		assertEquals(200, response2.getStatus());


		// Assert survey with id 8888 exists in our repository and then get the survey object
		assertTrue(surveyRepository.findById(8888).isPresent());
		Survey updatedSurvey = surveyRepository.findById(8888).get();
		//note: I called it updatedSurvey instead of addedSurvey bec this is for Update and addedSurvey was for Post (c above)


		// Assert the details of the surveys are correct
		assertEquals(8888, updatedSurvey.getSurveyId());
		assertEquals(6, updatedSurvey.getCustomer().getId()); //Cust has "id" which is from Person
		assertEquals(1, updatedSurvey.getCruise().getCruiseId());
		assertEquals("August 1, 2025", updatedSurvey.getDateOfSurvey());



		// IDEA: for Put, should u add it to the end of the addStudent() function (the Post part)
		// then u could make anoth JSON called studentJsonUpdated (with some changes to attrib.s
		//...but id would hav 2b same)
		// and do a mock Put of the updated json
		//then do the same stuff as the Post test (so check status 200 and check that it exists
		//and check that the attributes equal the !updated! attributes)
		//SEE above code for implementation of this Idea



	}





	// DELETE
	@Test
	void deleteSurvey() throws Exception{


		//INSERT INTO survey(cruiseId, customerId, questionId, qualitativeAnswer, quantitativeAnswer);


		// new as of start of Mar 31
		//INSERT INTO survey (surveyId, customerId, cruiseId, dateOfSurvey) VALUES (1, 6, 1, 'March 25, 2025');

		// Mar 31 TRYING:
		//Make a fake Survey----------------------------

		// make a take Cruise
		Cruise c = new Cruise();
		c.setCruiseId(1);
		c.setDestination("Rome");
		c.setDuration(500);
		c.setCruiseName("Cruise to Rome");

		//make a fake Customer
		Customer cust1 = new Customer();
		cust1.setId(1);
		cust1.setFirstName("Taylor");
		cust1.setLastName("Swift");
		cust1.setEmail("taylor@gmail");

		//Make a fake survey

		Survey s = new Survey();

		//we are doing setCruise() bec Survey has an attrib called "cruise" not "cruiseId"
		//we need to pass in a Cruise object! so pass in Cruise obj "c" which was defined above
		s.setCruise(c);

		s.setCustomer(cust1); //cust1 object was defined above

		s.setDateOfSurvey("Mar 25, 2025");

		//s.setQualitativeAnswer("The whole cruise was great!");
		//s.setQuantitativeAnswer(null);
		surveyRepository.save(s);
		//---------------------------------------------



		//do a mock delete
		MockHttpServletResponse response = mockMvc.perform(
						delete("/surveys/6").
								contentType("application/json"))
				.andReturn().getResponse();


		assertEquals(200, response.getStatus());
		assertTrue(surveyRepository.findById(6).isEmpty());
	}



}



