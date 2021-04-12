package com.project.service;

import com.project.interfaces.ITeamService;
import com.project.model.Team;
import com.project.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
    public boolean deleteTeam(Integer id){

        repository.deleteById(id);

        return true;
    }

    @Override
    public Team getTeamById(Integer id){
        return repository.findTeamById(id);
    }

    @Override
    public List<Team> getLeaderboard(){
        return repository.getLeaderboard();
    }

    @Override
    public void updateTeam(Team team) {
        Team updatedTeam = repository.findTeamById(team.getId());
        updatedTeam.setName(team.getName());
        updatedTeam.setTotalPts(team.getTotalPts());
        updatedTeam.setTotalAssists(team.getTotalAssists());
        updatedTeam.setTotalRebounds(team.getTotalRebounds());

        repository.save(updatedTeam);
    }

}
