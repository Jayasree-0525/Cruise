package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "CruiseLine")
public class CruiseLine {

    @Id
    @NotNull
    private int cruiseId;

    @Nullable
    private int duration; //in days

    @NotNull
    private String cruiseName;

//    @NotNull
//    private ArrayList<String> cruiseFeatures;


}
