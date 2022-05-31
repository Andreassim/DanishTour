package com.example.danishtour.wrapper;

import com.example.danishtour.entity.Team;
import com.example.danishtour.entity.TourResult;

import java.time.Duration;
import java.util.List;

public class TeamResultsWrapper {

    private Team team;

    private Duration totalTime;

    public Duration getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Duration totalTime) {
        this.totalTime = totalTime;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
