package com.project.service;

import com.project.interfaces.IPlayerTeamService;
import com.project.model.PlayerTeam;
import com.project.repository.PlayerTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerTeamService implements IPlayerTeamService {

    @Autowired
    private PlayerTeamRepository repository;

    @Override
    public boolean addPlayerTeam(List<PlayerTeam> playerTeam){
        repository.saveAll(playerTeam);
        return true;
    }
}