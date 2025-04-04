package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "staff")
public class Staff extends Person{
    private String position;
}


