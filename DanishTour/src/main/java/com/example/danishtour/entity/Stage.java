package com.example.danishtour.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int stageNumber;
    @OneToMany
    @JoinColumn(name = "stage_id")
    private List<StageResult> stageResults;

    @ManyToOne
    @JsonBackReference
    private Tour tour;

    private LocalTime startTime;


    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public List<StageResult> getResults() {
        return stageResults;
    }

    public void setResults(List<StageResult> stageResults) {
        this.stageResults = stageResults;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
