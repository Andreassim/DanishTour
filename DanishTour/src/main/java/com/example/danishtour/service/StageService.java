package com.example.danishtour.service;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Stage;
import com.example.danishtour.entity.StageResult;
import com.example.danishtour.repository.StageRepository;
import com.example.danishtour.repository.StageResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private StageResultRepository stageResultRepository;

    public Optional<Stage> findById(long id){
        return stageRepository.findById(id);
    }

    public StageResult updateStageResult(StageResult stageResult){
        return stageResultRepository.save(stageResult);
    }

    public List<StageResult> findAllbyStagesAndRider(List<Stage> stages, Rider rider){
        return stageResultRepository.findAllByStageIsInAndRiderIs(stages, rider);
    }
}
