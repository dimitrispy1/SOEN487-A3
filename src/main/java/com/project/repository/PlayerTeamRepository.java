package com.project.repository;

import com.project.model.PlayerTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTeamRepository extends CrudRepository<PlayerTeam, Long> {

}
