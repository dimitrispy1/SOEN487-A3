package com.project.service;

import com.project.interfaces.ITeamService;
import com.project.model.Team;
import com.project.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private TeamRepository repository;

    @Override
    public List<Team> findAll() {

        return (List<Team>) repository.findAll();
    }

    @Override
    public boolean addTeam(Team team) {
         repository.save(team);
         return true;
    }

    @Override
    public List<Team> getTeamsForUser(Integer id) {
        return repository.findByUserId(id);
    }

    @Override
    public boolean deleteTeam(Long id){

        boolean exists = repository.existsById(id);
        if(exists)
            repository.deleteById(id);
        return exists;
    }
}
