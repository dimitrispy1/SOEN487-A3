package com.project.repository;

import com.project.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findByUserId (Integer id);

    void deleteById(Integer id);

    Team findTeamById (Integer id);

    @Query("SELECT new com.project.model.Team (t.id, t.name, t.totalPts, t.totalAssists, t.totalRebounds, t.userId) FROM t_team t ORDER BY (t.totalPts + t.totalAssists + t.totalRebounds) DESC")
    List<Team> getLeaderboard();
}
