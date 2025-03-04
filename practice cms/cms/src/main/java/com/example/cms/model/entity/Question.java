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
@Table(name = "Question")
public class Question {

    @Id
    private int questionId;


    private String question;

    private String type; // qualitative or quantitative

    //@OneToMany(mappedBy = "Response")
    //@Nullable
    //private String qualitativeAnswer;
    //private List<Response> listOfresponses = new ArrayList<>();

    //@Nullable
    //private float quantitativeAnswer;



}
