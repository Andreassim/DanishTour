package com.example.danishtour.controller;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/rider")
public class RiderController {

    @Autowired
    private RiderService riderService;


    @PostMapping
    @RequestMapping("/new")
    public Rider createNew(@RequestBody Rider rider) {
        return riderService.updateRider(rider);
    }

    @GetMapping
    @RequestMapping("/all")
    public List<Rider> getAll() {
        List<Rider> riders = riderService.findAll();
        return riders;
    }

    @PutMapping("/update")
    public Rider updateRider(@RequestBody Rider rider) {
        return riderService.updateRider(rider);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRider(@PathVariable long id) {
        Optional<Rider> rider = riderService.findById(id);
        if (rider.isPresent()) {
            return new ResponseEntity<>(riderService.deleteRider(rider.get()), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
