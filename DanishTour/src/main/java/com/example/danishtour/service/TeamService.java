package com.example.danishtour.service;

import com.example.danishtour.entity.Team;
import com.example.danishtour.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;


    public Optional<Team> findById(Long id){
        return teamRepository.findById(id);
    }

}
