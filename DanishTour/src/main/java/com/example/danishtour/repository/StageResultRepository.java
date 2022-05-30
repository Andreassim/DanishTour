package com.example.danishtour.repository;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Stage;
import com.example.danishtour.entity.StageResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageResultRepository extends JpaRepository<StageResult, Long> {

    List<StageResult> findAllByStageIsIn(List<Stage> stages);
    List<StageResult> findAllByStageIsInAndRiderIs(List<Stage> stages, Rider rider);

    void deleteAllByRider(Rider rider);

}
