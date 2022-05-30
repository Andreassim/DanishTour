package com.example.danishtour.service;

import com.example.danishtour.entity.Tour;
import com.example.danishtour.repository.TourRepository;
import com.example.danishtour.wrapper.TourResultsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;


    public Tour updateTour(Tour tour){
        return tourRepository.save(tour);
    }

    public void deleteTour(Tour tour){
        tourRepository.delete(tour);
    }

    public Optional<Tour> tourById(long id){
        return tourRepository.findById(id);
    }

    public List<Tour> allTours(){
        return tourRepository.findAll();
    }


}
