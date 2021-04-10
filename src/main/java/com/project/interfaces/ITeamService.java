package com.project.interfaces;

import com.project.model.Team;

import java.util.List;

public interface ITeamService {

    List<Team> findAll();

    boolean addTeam(Team team);

    List<Team> getTeamsForUser(Integer id);

    boolean deleteTeam(Integer id);
}
