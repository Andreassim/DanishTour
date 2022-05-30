package com.example.danishtour.service;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Team;
import com.example.danishtour.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiderService {
    @Autowired
    private RiderRepository riderRepository;

    public List<Rider> findAll() {
        return riderRepository.findAll();
    }

    public Optional<Rider> findById(Long id) {
        return riderRepository.findById(id);
    }

    public Rider updateRider(Rider rider) {
        return riderRepository.save(rider);
    }

    public boolean deleteRider(Rider rider) {
        try {
            riderRepository.delete(rider);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Rider> findAllByTeam(Team team){
        return riderRepository.findAllByTeam(team);
    }

}
