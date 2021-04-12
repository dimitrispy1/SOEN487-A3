package com.project.service;

import com.project.interfaces.IPlayerTeamService;
import com.project.model.PlayerTeam;
import com.project.model.Team;
import com.project.repository.PlayerTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PlayerTeamService implements IPlayerTeamService {

    @Autowired
    private PlayerTeamRepository repository;

    @Override
    public boolean addPlayerTeam(List<PlayerTeam> playerTeam){
        repository.saveAll(playerTeam);
        return true;
    }

    @Override
    public void deleteAllByTeam(Team team) {
        repository.deleteAllByTeam(team);
    }


}