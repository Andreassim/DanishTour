package com.example.danishtour.repository;

import com.example.danishtour.entity.Stage;
import com.example.danishtour.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Long> {

    List<Stage> findAllByTour(Tour tour);
}
