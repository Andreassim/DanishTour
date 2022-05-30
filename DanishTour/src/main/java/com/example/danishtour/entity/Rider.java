package com.example.danishtour.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @ManyToOne()
    private Team team;

    @JsonBackReference
    @OneToMany(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "rider_id")
    private List<StageResult> stageResults;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "riders_id")
    private Tour tours;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Collection<TourResult> tourResult;

    public Long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }



}
