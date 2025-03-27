package com.example.cms.controller.dto;

import com.example.cms.model.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class SurveyDto {
    private int surveyId;
    private int customerId;
    private int cruiseId;
    private String dateOfSurvey;

}
