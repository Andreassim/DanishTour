package com.example.danishtour.repository;

import com.example.danishtour.entity.Rider;
import com.example.danishtour.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiderRepository extends JpaRepository<Rider, Long> {

    List<Rider> findAllByTeam(Team team);

    List<Rider> findAllByIdIsNotIn(List<Long> ids);

}
