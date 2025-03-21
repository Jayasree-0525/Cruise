package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Survey")
public class Survey {

    @Embedded
    private int surveyId;
    //@Id


    //Isn't this OneToOne relationship??
    @OneToMany
    @MapsId("customerId")
    @JoinColumn(name = "customerId")
    private Customer customer;


    @ManyToOne
    @MapsId("cruiseId")
    @JoinColumn(name = "cruiseId")
    private CruiseLine cruise;


    @OneToMany
    @MapsId("responseId")
    @JoinColumn(name = "responseId")
    private Response response;

    /*
    @ManyToMany
    @MapsId("questionId")
    @JoinColumn(name = "questionId")
    private Question question;*/


    //@ManyToMany(mappedBy = "Question")
   // private List<Question> listOfQuestions = new ArrayList<>();


    //private Question q; <- got rid of Question entity
    //@NotNull
    //private int questionId;

    //@NotNull
    //private String question;

    //uncommented Mar 21
    /*@Nullable
    private String qualitativeAnswer;

    @Nullable
    private Float quantitativeAnswer;*/

}
