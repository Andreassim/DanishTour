package com.example.danishtour.repository;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Team;
import com.example.danishtour.entity.Tour;
import com.example.danishtour.entity.TourResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourResultRepository extends JpaRepository<TourResult, Long> {

    List<TourResult> findAllByTourOrderByTotalTime (Tour tour);

    List<TourResult> findAllByTourOrderByTotalMountainPointsDesc(Tour tour);

    List<TourResult> findAllByTourOrderByTotalSprintPointsDesc(Tour tour);

    List<TourResult> findAllByTourOrderByRiderAge(Tour tour);

    Optional<TourResult> findByRider(Rider rider);

    List<TourResult> findByRiderTeamOrderByTotalTime(Team team);
}
