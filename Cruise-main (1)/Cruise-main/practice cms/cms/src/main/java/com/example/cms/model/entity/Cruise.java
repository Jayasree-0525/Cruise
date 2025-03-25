package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Cruise")
public class Cruise {

    @Id
    @NotNull
    private int cruiseId;

    @Nullable
    private int duration; //in days

    @NotNull
    private String cruiseName;

    @NotNull
    private String destination;


}
