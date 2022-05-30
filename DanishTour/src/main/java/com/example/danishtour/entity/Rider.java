package com.example.danishtour.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rider {

    @Id
    private Long id;

    private String name;

    @OneToOne
    @JsonBackReference
    private Team team;


}
