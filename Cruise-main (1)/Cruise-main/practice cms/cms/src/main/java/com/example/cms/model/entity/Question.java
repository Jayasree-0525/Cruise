package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "questions")
public class Question {

    @Id
    @NotEmpty
    private int questionId;

    @NotEmpty
    private String question;

    @NotEmpty
    private String type; // qualitative or quantitative

    public Question(int questionId, String question, String type){
        this.questionId = questionId;
        this.question = question;
        this.type = type;
    }

    //@OneToMany(mappedBy = "Response")
    //@Nullable
    //private String qualitativeAnswer;
    //private List<Response> listOfresponses = new ArrayList<>();

    //@Nullable
    //private float quantitativeAnswer;



}
