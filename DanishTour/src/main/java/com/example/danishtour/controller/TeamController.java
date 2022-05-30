package com.example.danishtour.controller;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Team;
import com.example.danishtour.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    @RequestMapping("/riders/{id}")
    public ResponseEntity<List<Rider>> getAllTeamRiders(@PathVariable long id){
        Optional<Team> optionalTeam =  teamService.findById(id);
        if(optionalTeam.isPresent()){
            return new ResponseEntity<>(optionalTeam.get().getRiders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
