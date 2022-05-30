package com.example.danishtour.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;

@Entity(name = "result")
public class StageResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Rider rider;

    private Duration completionTime;

    private int mountainPoints;

    private int sprintPoints;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    @JsonBackReference
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Duration getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Duration completionTime) {
        this.completionTime = completionTime;
    }

    public int getMountainPoints() {
        return mountainPoints;
    }

    public void setMountainPoints(int mountainPoints) {
        this.mountainPoints = mountainPoints;
    }

    public int getSprintPoints() {
        return sprintPoints;
    }

    public void setSprintPoints(int sprintPoints) {
        this.sprintPoints = sprintPoints;
    }
}
