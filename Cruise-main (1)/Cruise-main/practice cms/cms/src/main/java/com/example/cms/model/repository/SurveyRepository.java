package com.example.cms.model.repository;

import com.example.cms.model.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer>{

    // delete survey by customer id
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM survey WHERE customerId = :cId", nativeQuery = true)
    void deleteSurveyByCustomerId(@Param("cId") int cId);

}
