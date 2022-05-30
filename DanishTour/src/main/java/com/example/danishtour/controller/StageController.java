package com.example.danishtour.controller;

import com.example.danishtour.entity.Stage;
import com.example.danishtour.entity.StageResult;
import com.example.danishtour.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/stage")
public class StageController {

    @Autowired
    private StageService stageService;

    @PostMapping
    @RequestMapping("/{id}/addResult")
    public ResponseEntity<StageResult> addResult(@PathVariable Long id, @RequestBody StageResult stageResult){
        Optional<Stage> optionalStage = stageService.findById(id);
        if(optionalStage.isPresent()){
            List<StageResult> results = stageService.findAllbyStagesAndRider(optionalStage.stream().toList(), stageResult.getRider());
            stageResult.setStage(optionalStage.get());
            StageResult result;
            if(!results.isEmpty()){
                result = results.get(0);
                result.setMountainPoints(stageResult.getMountainPoints());
                result.setSprintPoints(stageResult.getSprintPoints());
                result.setCompletionTime(stageResult.getCompletionTime());
                result = stageService.updateStageResult(result);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            result = stageService.updateStageResult(stageResult);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
