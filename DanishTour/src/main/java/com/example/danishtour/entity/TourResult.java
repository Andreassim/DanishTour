package com.example.danishtour.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;

@Entity
public class TourResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    @JsonBackReference
    private Tour tour;

    private Duration totalTime;

    private int totalSpringPoints;

    private int totalMountainPoints;

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Duration getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Duration totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalSprintPoints() {
        return totalSpringPoints;
    }

    public void setTotalSprintPoints(int totalSpringPorints) {
        this.totalSpringPoints = totalSpringPorints;
    }

    public int getTotalMountainPoints() {
        return totalMountainPoints;
    }

    public void setTotalMountainPoints(int totalMountainPoints) {
        this.totalMountainPoints = totalMountainPoints;
    }
}
