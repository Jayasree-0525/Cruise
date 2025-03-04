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

    @Id
    private int surveyId;

    @ManyToOne
//    @MapsId("customerId")
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
//    @MapsId("cruiseId")
    @JoinColumn(name = "cruiseId")
    private CruiseLine cruiseLine;


    //@OneToMany(mappedBy = "Question")
    //private List<Question> listOfQuestions = new ArrayList<>();


    //private Question q; <- got rid of Question entity
    @NotNull
    private int questionId;

    @NotNull
    private String question;

    @Nullable
    private String qualitativeAnswer;

    @Nullable
    private Float quantitativeAnswer;

}
