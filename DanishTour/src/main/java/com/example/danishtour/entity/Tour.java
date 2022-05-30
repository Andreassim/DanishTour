package com.example.danishtour.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "tour_id")
    private List<Stage> stages;

    @OneToMany
    private List<Team> teams;

    @OneToMany
    private List<Rider> riders;

    @OneToMany
    @JoinColumn(name = "tour_id")
    @OrderBy("totalTime")
    private List<TourResult> tourResults;


    public long getId() {
        return id;
    }

    public List<TourResult> getTourResults() {
        return tourResults;
    }

    public void setTourResults(List<TourResult> tourResults) {
        this.tourResults = tourResults;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
}
