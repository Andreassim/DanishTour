package com.example.danishtour.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Result {

    @Id
    private long id;

    @ManyToOne
    private Rider rider;

}
