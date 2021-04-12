package com.project.interfaces;

import com.project.model.PlayerTeam;
import com.project.model.Team;

import java.util.List;

public interface IPlayerTeamService {

    boolean addPlayerTeam(List<PlayerTeam> playerTeam);

    void deleteAllByTeam (Team team);
}
