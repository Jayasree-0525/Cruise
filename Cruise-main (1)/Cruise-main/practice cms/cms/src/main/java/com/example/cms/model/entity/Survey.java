package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Survey")
public class Survey {

    @Id
    private int surveyId;

    @NotEmpty
    private String date;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @MapsId("cruiseId")
    @JoinColumn(name = "cruiseId")
    private Cruise cruise;

    //@OneToMany
   // @MapsId("responseId")
   // @JoinColumn(name = "responseId")
    //private Response response;

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
