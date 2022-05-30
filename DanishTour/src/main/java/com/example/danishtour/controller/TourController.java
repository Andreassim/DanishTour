package com.example.danishtour.controller;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Tour;
import com.example.danishtour.entity.TourResult;
import com.example.danishtour.service.RiderService;
import com.example.danishtour.service.TourResultService;
import com.example.danishtour.service.TourService;
import com.example.danishtour.wrapper.TourResultsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private RiderService riderService;

    @Autowired
    private TourResultService tourResultService;

    @PostMapping
    @RequestMapping("/new")
    public Tour createNew(@RequestBody Tour tour) {
        return tourService.updateTour(tour);
    }

    @GetMapping
    @RequestMapping("/all")
    public List<Tour> findAll() {
        return tourService.allTours();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Tour> findById(@PathVariable Long id) {
        Optional<Tour> optionalTour = tourService.tourById(id);
        if (optionalTour.isPresent()) {
            return new ResponseEntity<>(optionalTour.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    @RequestMapping("/{id}/results")
    public ResponseEntity<TourResultsWrapper> getAllResults(@PathVariable long id) {
        Optional<Tour> optionalTour = tourService.tourById(id);
        if (optionalTour.isPresent()) {
            TourResultsWrapper wrapper = tourResultService.getTourResultsSorted(optionalTour.get());
            return new ResponseEntity<>(wrapper, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    @RequestMapping("/{id}/fastest")
    public ResponseEntity<List<TourResult>> getFastest(@PathVariable long id) {
        Optional<Tour> optionalTour = tourService.tourById(id);
        if (optionalTour.isPresent()) {
            List<TourResult> tourResults = tourResultService.getTourResultsFastest(optionalTour.get());
            return new ResponseEntity<>(tourResults, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    @RequestMapping("/{id}/availableRiders")
    public ResponseEntity<List<Rider>> getAvailableRider(@PathVariable long id) {
        Optional<Tour> optionalTour = tourService.tourById(id);
        if (optionalTour.isPresent()) {
            List<Rider> availableRiders = riderService.findRidersNotInList(optionalTour.get().getRiders());
            return new ResponseEntity<>(availableRiders, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PostMapping
    @RequestMapping("/{id}/addRider")
    public ResponseEntity<Tour> addRiderToTour(@PathVariable long id, @RequestBody Rider rider) {
        Optional<Tour> optionalTour = tourService.tourById(id);
        if (optionalTour.isPresent()) {
            Optional<Rider> optionalRider = riderService.findById(rider.getId());
            if (optionalRider.isPresent()) {
                Tour tour = optionalTour.get();
                tour.getRiders().add(optionalRider.get());
                tourService.updateTour(tour);
                return new ResponseEntity<>(tour, HttpStatus.OK);

            }
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping
    @RequestMapping("/{id}/update")
    public ResponseEntity<Tour> updateTourResults(@PathVariable long id){
        Optional<Tour> optionalTour = tourService.tourById(id);
        if(optionalTour.isPresent()){
            tourResultService.updateResults(optionalTour.get());
            return new ResponseEntity<>(tourService.updateTour(optionalTour.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
