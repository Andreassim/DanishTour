package com.example.danishtour.config;

import com.example.danishtour.entity.*;
import com.example.danishtour.repository.*;
import com.example.danishtour.service.TourResultService;
import com.example.danishtour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;


@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StageResultRepository stageResultRepository;

    @Autowired
    private TourResultService tourResultService;



    @Override
    public void run(String... args) throws Exception {
        Tour tour = new Tour();
        tour.setName("Den Danske Tour");
        tourRepository.save(tour);

        tour.setStages(new ArrayList<>());

        Stage stage1 = new Stage();
        stage1.setStageNumber(1);
        stage1.setStartTime(LocalTime.NOON);
        stage1.setTour(tour);

        Stage stage2 = new Stage();
        stage2.setStageNumber(2);
        stage2.setStartTime(LocalTime.NOON);
        stage2.setTour(tour);

        Stage stage3 = new Stage();
        stage3.setStageNumber(3);
        stage3.setStartTime(LocalTime.NOON);
        stage3.setTour(tour);

        tour.getStages().add(stage1);
        tour.getStages().add(stage2);
        tour.getStages().add(stage3);

        stageRepository.saveAll(tour.getStages());


        Team team1 = new Team();
        team1.setName("Wolt-Rynkeby Cycling Team");
        team1.setAcronym("WRC");

        Team team2 = new Team();
        team2.setName("Microsoft-Shopify Pro Cycling");
        team2.setAcronym("MSC");

        tour.setTeams(new ArrayList<>());
        tour.getTeams().add(team1);
        tour.getTeams().add(team2);

        teamRepository.save(team1);
        teamRepository.save(team2);

        tourRepository.save(tour);

        Rider rider1 = new Rider();
        rider1.setName("Poul Henning Thomsen");
        rider1.setTeam(team1);
        rider1.setAge(22);

        Rider rider2 = new Rider();
        rider2.setTeam(team2);
        rider2.setAge(31);
        rider2.setName("Preben Sigfred");

        tour.setRiders(new ArrayList<>());

        riderRepository.save(rider1);
        riderRepository.save(rider2);

        tour.getRiders().add(rider1);
        tour.getRiders().add(rider2);
        tourRepository.save(tour);

        StageResult stageResult = new StageResult();
        stageResult.setCompletionTime(Duration.between(stage1.getStartTime(),LocalTime.of(15,30,20)));
        stageResult.setRider(rider1);
        stageResult.setStage(stage1);
        stageResult.setSprintPoints(1);

        stageResultRepository.save(stageResult);

        stageResult = new StageResult();
        stageResult.setCompletionTime(Duration.between(stage1.getStartTime(),LocalTime.of(15,38,10)));
        stageResult.setRider(rider2);
        stageResult.setStage(stage1);
        stageResult.setSprintPoints(0);

        stageResultRepository.save(stageResult);

        stageResult = new StageResult();
        stageResult.setCompletionTime(Duration.between(stage2.getStartTime(),LocalTime.of(12,30,20)));
        stageResult.setRider(rider1);
        stageResult.setStage(stage2);
        stageResult.setSprintPoints(0);
        stageResult.setMountainPoints(2);

        stageResultRepository.save(stageResult);

        stageResult = new StageResult();
        stageResult.setCompletionTime(Duration.between(stage2.getStartTime(),LocalTime.of(12,31,30)));
        stageResult.setRider(rider2);
        stageResult.setStage(stage2);
        stageResult.setSprintPoints(3);
        stageResult.setMountainPoints(0);

        stageResultRepository.save(stageResult);


        stageResult = new StageResult();
        stageResult.setCompletionTime(Duration.between(stage3.getStartTime(),LocalTime.of(14,21,34)));
        stageResult.setRider(rider1);
        stageResult.setStage(stage3);
        stageResult.setSprintPoints(0);
        stageResult.setMountainPoints(10);

        stageResultRepository.save(stageResult);

        stageResult = new StageResult();
        stageResult.setCompletionTime(Duration.between(stage3.getStartTime(),LocalTime.of(14,19,20)));
        stageResult.setRider(rider2);
        stageResult.setStage(stage3);
        stageResult.setSprintPoints(0);
        stageResult.setMountainPoints(7);

        stageResultRepository.save(stageResult);

        tourResultService.updateResults(tour);


    }
}
