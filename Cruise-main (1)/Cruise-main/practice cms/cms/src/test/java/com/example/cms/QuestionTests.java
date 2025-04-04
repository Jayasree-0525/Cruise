package com.example.cms;

import com.example.cms.model.entity.Question;
import com.example.cms.model.entity.Survey;
import com.example.cms.model.repository.CustomerRepository;

import com.example.cms.model.repository.QuestionRepository;
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


import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.cms.model.entity.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

class QuestionTests {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void addQuestions() throws Exception{

        ObjectNode questionJson = objectMapper.createObjectNode();
        questionJson.put("questionId", 8888);
        questionJson.put("question", "Rate how clean the room was");
        questionJson.put("type", "quantitative");


        MockHttpServletResponse response = mockMvc.perform(
                        post("/questions").
                                contentType("application/json").
                                content(questionJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        assertTrue(questionRepository.findById(8888).isPresent());
        Question addedQuestion = questionRepository.findById(8888).get();

        assertEquals(8888, addedQuestion.getQuestionId());
        assertEquals("Rate how clean the room was", addedQuestion.getQuestion());
        assertEquals("quantitative", addedQuestion.getType());
    }

    @Test
    void getQuestions() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/questions/8888"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(8888, receivedJson.get("questionId").intValue());
        assertEquals("Rate how clean the room was", receivedJson.get("question").textValue());
        assertEquals("quantitative", receivedJson.get("type").textValue());
    }

    @Test
    void deleteQuestions() throws Exception{

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/questions/8888").
                                contentType("application/json"))
                .andReturn().getResponse();


        assertEquals(200, response.getStatus());
        assertTrue(questionRepository.findById(8888).isEmpty());
    }

    @Test
    void updateQuestions() throws Exception {

        ObjectNode questionJsonUpdated = objectMapper.createObjectNode();
        questionJsonUpdated.put("questionId", 8888);
        questionJsonUpdated.put("question", "Rate on a scale of 1-5 how clean your room was");
        questionJsonUpdated.put("type", "quantitative");


        MockHttpServletResponse response2 = mockMvc.perform(
                        //perform an update aka put instead of post
                        put("/questions/8888").
                                contentType("application/json").
                                content(questionJsonUpdated.toString()))
                .andReturn().getResponse();

        assertEquals(200, response2.getStatus());

        assertTrue(questionRepository.findById(8888).isPresent());
        Question updatedQuestion = questionRepository.findById(8888).get();

        assertEquals(8888, updatedQuestion.getQuestionId());
        assertEquals("Rate on a scale of 1-5 how clean your room was", updatedQuestion.getQuestion());
        assertEquals("quantitative", updatedQuestion.getType());
    }
}
