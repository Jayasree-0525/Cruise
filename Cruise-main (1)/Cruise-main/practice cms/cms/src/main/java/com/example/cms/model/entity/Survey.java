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

    //@Embedded
    @Id
    private int surveyId;

    @OneToMany
    @MapsId("customerId")
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany
    @MapsId("responseId")
    @JoinColumn(name = "responseId")
    private Response response;


    @OneToMany
    @MapsId("questionId")
    @JoinColumn(name = "questionId")
    private Question question;


    //@ManyToMany(mappedBy = "Question")
   // private List<Question> listOfQuestions = new ArrayList<>();


    //private Question q; <- got rid of Question entity
    //@NotNull
    //private int questionId;

    //@NotNull
    //private String question;

    //@Nullable
    //private String qualitativeAnswer;

   // @Nullable
    //private Float quantitativeAnswer;

}
