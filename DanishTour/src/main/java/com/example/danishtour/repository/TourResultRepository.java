package com.example.danishtour.repository;

import com.example.danishtour.entity.Tour;
import com.example.danishtour.entity.TourResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourResultRepository extends JpaRepository<TourResult, Long> {

    List<TourResult> findAllByTourOrderByTotalTime (Tour tour);

    List<TourResult> findAllByTourOrderByTotalMountainPoints (Tour tour);

    List<TourResult> findAllByTourOrderByTotalSpringPoints (Tour tour);

}
