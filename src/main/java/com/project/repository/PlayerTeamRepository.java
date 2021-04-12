package com.project.repository;

import com.project.model.PlayerTeam;
import com.project.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTeamRepository extends CrudRepository<PlayerTeam, Long> {

    void deleteAllByTeam(Team team);
}
