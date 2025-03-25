package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "responses")
public class Response {

    @EmbeddedId
    ResponseKey responseId;

    @ManyToOne
    @MapsId("surveyId")
    @JoinColumn(name = "surveyId")
    private Survey survey;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "questionId")
    private Question question;

    @NotNull
    private String response;
}
