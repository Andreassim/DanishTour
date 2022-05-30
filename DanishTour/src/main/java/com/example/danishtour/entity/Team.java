package com.example.danishtour.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Team {

    @Id
    private long id;

    private String name;

    @OneToMany
    private List<Rider> riders;

}
