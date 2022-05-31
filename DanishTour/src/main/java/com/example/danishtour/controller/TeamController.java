package com.example.danishtour.controller;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Team;
import com.example.danishtour.entity.TourResult;
import com.example.danishtour.service.TeamService;
import com.example.danishtour.service.TourResultService;
import com.example.danishtour.wrapper.TeamResultsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TourResultService tourResultService;

    @GetMapping
    @RequestMapping("/{id}/riders")
    public ResponseEntity<List<Rider>> getAllTeamRiders(@PathVariable long id) {
        Optional<Team> optionalTeam = teamService.findById(id);
        if (optionalTeam.isPresent()) {
            return new ResponseEntity<>(optionalTeam.get().getRiders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @RequestMapping("/fastest")
    public List<TeamResultsWrapper> getFastestTeams(){
        List<TeamResultsWrapper> result = new ArrayList<>();
        List<Team> teams = teamService.findAll();
        for (Team team: teams) {
            List<TourResult> tourResults = tourResultService.findAllByTeam(team);
            if(tourResults.size()>5){
                tourResults = tourResults.subList(0,4);
            }
            Duration totalTime = Duration.parse("PT0S");
            for(TourResult tr:tourResults){
                totalTime = totalTime.plus(tr.getTotalTime());

            }
            TeamResultsWrapper wrapper = new TeamResultsWrapper();
            wrapper.setTeam(team);
            wrapper.setTotalTime(totalTime);
            result.add(wrapper);
        }
        result.sort(Comparator.comparing(TeamResultsWrapper::getTotalTime));
        return result;
    }
}
