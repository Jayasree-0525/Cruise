package com.example.cms;

import com.example.cms.model.entity.*;
import com.example.cms.model.repository.QuestionRepository;
import com.example.cms.model.repository.ResponseRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ResponseTests {
    // reference: SurveyTests

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SurveyRepository surveyRepository;

    //need to add because we make a mock question in Delete
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResponseRepository responseRepository;

    // THIS IS the only CRUD func that has been edited to be for Response (not Survey)
    @Test
    void deleteResponses() throws Exception{

        Survey s = new Survey();
        s.setSurveyId(2);

        Question q1 = new Question();
        q1.setQuestionId(5);

        ResponseKey compositeKey = new ResponseKey(2, 5);

        Response r1 = new Response();
        r1.setSurvey(s); //pass in the survey object we created
        r1.setQuestion(q1);
        r1.setResponse("Pretty good!");
        r1.setResponseId(compositeKey);
        responseRepository.save(r1);

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/responses/2/5").
                                contentType("application/json"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        //...if it returns null (when u try to get it) then u successfully deleted it
        assertTrue(responseRepository.findById(compositeKey).isEmpty());
        //MAYB NEED try insomnia to c if its null vs empty etc is returned when u get someth that don exist


    }

    @Test
    void addResponses() throws Exception{

        ObjectNode responseJson = objectMapper.createObjectNode();
        responseJson.put("surveyId", 2);
        responseJson.put("questionId", 5);
        responseJson.put("response", "Good");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/responses").
                                contentType("application/json").
                                content(responseJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ResponseKey responseKey = new ResponseKey(2, 5);

        assertTrue(responseRepository.findById(responseKey).isPresent());
        Response addedResponse = responseRepository.findById(responseKey).get();

        assertEquals(2, addedResponse.getSurvey().getSurveyId());
        assertEquals(5, addedResponse.getQuestion().getQuestionId());
        assertEquals("Good", addedResponse.getResponse());

    }

    @Test
    void getResponses() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/responses/1/1"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(1, receivedJson.get("survey").get("surveyId").intValue());
        assertEquals(1, receivedJson.get("question").get("questionId").intValue());
        assertEquals("1", receivedJson.get("response").textValue());
    }

    @Test
    void updateResponses() throws Exception {

        ObjectNode responseJsonUpdated = objectMapper.createObjectNode();
        responseJsonUpdated.put("surveyId", 1);
        responseJsonUpdated.put("questionId", 1);
        responseJsonUpdated.put("response", "2");

        MockHttpServletResponse response2 = mockMvc.perform(
                        //perform an update aka put instead of post
                        put("/responses/1/1").
                                contentType("application/json").
                                content(responseJsonUpdated.toString()))
                .andReturn().getResponse();

        assertEquals(200, response2.getStatus());

        ResponseKey responseKey = new ResponseKey(1, 1);

        assertTrue(responseRepository.findById(responseKey).isPresent());
        Response updatedResponse = responseRepository.findById(responseKey).get();

        assertEquals(1, updatedResponse.getSurvey().getSurveyId());
        assertEquals(1, updatedResponse.getQuestion().getQuestionId());
        assertEquals("2", updatedResponse.getResponse());
    }
}



