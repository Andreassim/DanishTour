package com.example.danishtour.service;

import com.example.danishtour.entity.*;
import com.example.danishtour.repository.StageRepository;
import com.example.danishtour.repository.StageResultRepository;
import com.example.danishtour.repository.TourResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class TourResultService {

    @Autowired
    private TourResultRepository tourResultRepository;

    @Autowired
    private StageResultRepository stageResultRepository;

    @Autowired
    private StageRepository stageRepository;


    public List<TourResult> updateResults(Tour tour) {
        List<TourResult> tourResults = new ArrayList<>();
        for (Rider rider : tour.getRiders()) {
            TourResult tourResult = new TourResult();
            tourResult.setTotalTime(Duration.ZERO);
            List<Stage> stages = stageRepository.findAllByTour(tour);
            List<StageResult> results = stageResultRepository.findAllByStageIsInAndRiderIs(stages, rider);
            tourResult.setRider(rider);
            tourResult.setTour(tour);
            for (StageResult sr : results) {
                tourResult.setTotalSprintPoints(tourResult.getTotalSprintPoints()+ sr.getSprintPoints());
                tourResult.setTotalMountainPoints(tourResult.getTotalMountainPoints()+ sr.getMountainPoints());
                tourResult.setTotalTime(tourResult.getTotalTime().plus(sr.getCompletionTime()));
            }
            tourResults.add(tourResult);
        }

        tourResultRepository.saveAll(tourResults);
        return tourResults;

    }
}
