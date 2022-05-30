package com.example.danishtour.controller;

import com.example.danishtour.entity.Tour;
import com.example.danishtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @PostMapping
    @RequestMapping("/new")
    public Tour createNew(@RequestBody Tour tour){
        return tourService.updateTour(tour);
    }

    @GetMapping
    @RequestMapping("/all")
    public List<Tour> findAll(){
        return tourService.allTours();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Tour> findById(@PathVariable Long id){
        Optional<Tour> optionalTour = tourService.tourById(id);
        if(optionalTour.isPresent()){
            return new ResponseEntity<>(optionalTour.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
