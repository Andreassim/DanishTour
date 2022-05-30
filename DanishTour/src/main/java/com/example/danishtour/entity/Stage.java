package com.example.danishtour.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Stage {

    @Id
    private long id;

    @OneToMany
    private List<Result> results;

}
