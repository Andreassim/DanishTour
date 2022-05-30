package com.example.danishtour.wrapper;

import com.example.danishtour.entity.TourResult;

import java.util.List;

public class TourResultsWrapper {

    private List<TourResult> totalTime;
    private List<TourResult> mountainPoints;
    private List<TourResult> sprintPoints;
    private List<TourResult> age; //TODO Figure this one out

    public List<TourResult> getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(List<TourResult> totalTime) {
        this.totalTime = totalTime;
    }

    public List<TourResult> getMountainPoints() {
        return mountainPoints;
    }

    public void setMountainPoints(List<TourResult> mountainPoints) {
        this.mountainPoints = mountainPoints;
    }

    public List<TourResult> getSprintPoints() {
        return sprintPoints;
    }

    public void setSprintPoints(List<TourResult> sprintPoints) {
        this.sprintPoints = sprintPoints;
    }

    public List<TourResult> getAge() {
        return age;
    }

    public void setAge(List<TourResult> age) {
        this.age = age;
    }
}
