package com.example.danishtour.service;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Team;
import com.example.danishtour.entity.Tour;
import com.example.danishtour.repository.RiderRepository;
import com.example.danishtour.repository.StageResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RiderService {
    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private TourService tourService;



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
            List<Tour> tours = tourService.allTours();
            for(Tour tour : tours){
                if(tour.getRiders().contains(rider)){
                    tour.getRiders().remove(rider);
                    tourService.updateTour(tour);
                };
            }
            riderRepository.delete(rider);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Rider> findAllByTeam(Team team){
        return riderRepository.findAllByTeam(team);
    }

    public List<Rider> findRidersNotInList(List<Rider> list){
        List<Long> longs = new ArrayList<>();
        for (Rider rider: list) {
            longs.add(rider.getId());
        }
        return riderRepository.findAllByIdIsNotIn(longs);
    }

}
