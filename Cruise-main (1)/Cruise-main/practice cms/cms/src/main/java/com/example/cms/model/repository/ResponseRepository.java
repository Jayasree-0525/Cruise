package com.example.cms.model.repository;

import com.example.cms.model.entity.Response;
import com.example.cms.model.entity.ResponseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, ResponseKey> {

    //Delete customer process
    @Query(value = "SELECT s.surveyId FROM survey s WHERE s.customerId = :cId", nativeQuery = true)
    List<Integer> findSurveyIdsByCustomerId(@Param("cId") int cId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM responses WHERE surveyId IN (:surveyIds)", nativeQuery = true)
    void deleteResponsesBySurveyIds(@Param("surveyIds") List<Integer> surveyIds);

    //Delete survey process
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM responses WHERE surveyId = :sId", nativeQuery = true)
    void deleteResponsesBySingleSurveyId(@Param("sId") int sId);

    //Delete question process
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM responses WHERE questionId = :qId", nativeQuery = true)
    void deleteResponsesBySingleQuestionId(@Param("qId") int qId);

    //Delete response process
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM responses WHERE questionId = :questionId AND surveyId = :surveyId", nativeQuery = true)
    void deleteResponseByQuestionAndSurvey(@Param("questionId") int questionId, @Param("surveyId") int surveyId);

    // Get average response for a given question and cruise
    @Query(value = "SELECT AVG(CAST(r.response AS FLOAT)) " +
            "FROM responses r " +
            "JOIN survey s ON r.surveyId = s.surveyId " +
            "WHERE r.questionId = :qId AND s.cruiseId = :cruiseId",
            nativeQuery = true)
    float averageResponseByQuestionAndCruise(@Param("qId") int questionId, @Param("cruiseId") int cruiseId);

    // Get min response for a given question and cruise
    @Query(value = "SELECT MIN(CAST(r.response AS FLOAT)) " +
            "FROM responses r " +
            "JOIN survey s ON r.surveyId = s.surveyId " +
            "WHERE r.questionId = :qId AND s.cruiseId = :cruiseId",
            nativeQuery = true)
    float minResponseByQuestionAndCruise(@Param("qId") int questionId, @Param("cruiseId") int cruiseId);

    // Get max response for a given question and cruise
    @Query(value = "SELECT MAX(CAST(r.response AS FLOAT)) " +
            "FROM responses r " +
            "JOIN survey s ON r.surveyId = s.surveyId " +
            "WHERE r.questionId = :qId AND s.cruiseId = :cruiseId",
            nativeQuery = true)
    float maxResponseByQuestionAndCruise(@Param("qId") int questionId, @Param("cruiseId") int cruiseId);


    // quantitative - average
    @Query(value = "select AVG(CAST(response AS float)) from responses " +
            "where questionId = :qId", nativeQuery = true)
    float averageFunc(@Param("qId") int qId);

    // Get standard dev response for a given question and cruise
    @Query(value = "SELECT STDDEV_SAMP(CAST(r.response AS FLOAT)) " +
            "FROM responses r " +
            "JOIN survey s ON r.surveyId = s.surveyId " +
            "WHERE r.questionId = :qId AND s.cruiseId = :cruiseId",
            nativeQuery = true)
    float stdevResponseByQuestionAndCruise(@Param("qId") int questionId, @Param("cruiseId") int cruiseId);

    // quantitative - max
    @Query(value = "select MAX(CAST(response AS float)) from responses " +
            "where questionId = :qId", nativeQuery = true)
    float maxFunc(@Param("qId") int qId);

    // quantitative - min
    @Query(value = "select MIN(CAST(response AS float)) from responses " +
            "where questionId = :qId", nativeQuery = true)
    float minFunc(@Param("qId") int qId);

    // quantitative - count responses per question
    @Query(value = "select COUNT(response) from responses " +
            "where questionId = :qId", nativeQuery = true)
    int specificCountFunc(@Param("qId") int qId);

    // quantitative - count total number of responses
    @Query(value = "select COUNT(response) from responses", nativeQuery = true)
    int totalCountFunc();

    // quantitative - sample std dev
    @Query(value = "select STDDEV_SAMP(CAST(response AS float)) from responses " +
            "where questionId = :qId", nativeQuery = true)
    float sdFunc(@Param("qId") int qId);


    // qualitative - search for instances of a word
    @Query(value = "select * from responses r " +
            "where lower(r.response) like lower(concat('%', :searchTerm, '%')) ", nativeQuery = true)
    List<Response> searchQual(@Param("searchTerm") String searchTerm);


    // qualitative - count rows containing a word
    @Query(value = "select COUNT(response) from responses r " +
            "where lower(r.response) like lower(concat('%', :searchTerm, '%')) ", nativeQuery = true)
    int countQualWord(@Param("searchTerm") String searchTerm);



}
