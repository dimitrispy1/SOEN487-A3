package com.project.controller;

import com.project.interfaces.IPlayerTeamService;
import com.project.interfaces.ITeamService;
import com.project.model.*;
import com.project.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TeamController {

    @Autowired
    private ITeamService teamService;

    @Autowired
    private IPlayerTeamService playerTeamService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/teams")
    public UserResponse getTeamsForUser(@RequestHeader("Authorization") String authHeader) {

        User user = tokenService.validateToken(authHeader.split(" ")[1]);
        UserResponse userResponse = new UserResponse();

        if(user != null) {
            List<Team> teams = teamService.getTeamsForUser(user.getId());

            userResponse.setStatus(true);
            userResponse.setData(teams);
        }else{
            userResponse.setStatus(false);
            userResponse.setMessage("Invalid Token");
        }

        return userResponse;
    }

    @PostMapping(value="/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse addTeam(@RequestBody Team team, @RequestHeader("Authorization") String authHeader) {
        User user = tokenService.validateToken(authHeader.split(" ")[1]);
        UserResponse userResponse = new UserResponse();

        if(user != null) {
            team.setId(user.getId());
            teamService.addTeam(team);

            List<PlayerTeam> playerTeams = team.getPlayers();
            for (PlayerTeam playerTeam : playerTeams) {
                PlayerTeamKey key = new PlayerTeamKey(playerTeam.getPlayer().getId(), playerTeam.getTeam().getId());
                playerTeam.setId(key);
            }
            playerTeamService.addPlayerTeam(playerTeams);

            userResponse.setStatus(true);
            userResponse.setMessage("Team added");
        }else{
            userResponse.setStatus(false);
            userResponse.setMessage("Invalid Token");
        }

        return userResponse;
    }

    @PutMapping(value="/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse editTeam(@RequestBody Team team) {
        teamService.addTeam(team);

        List<PlayerTeam> playerTeams = team.getPlayers();
        for (PlayerTeam playerTeam : playerTeams) {
            PlayerTeamKey key = new PlayerTeamKey(playerTeam.getPlayer().getId(), playerTeam.getTeam().getId());
            playerTeam.setId(key);
        }
        playerTeamService.addPlayerTeam(playerTeams);

        UserResponse userResponse = new UserResponse();
        userResponse.setStatus(true);
        userResponse.setMessage("Team added");

        return userResponse;
    }

    @DeleteMapping("/team")
    public UserResponse deleteTeam(@RequestParam Integer id, @RequestHeader("Authorization") String authHeader) {
        User user = tokenService.validateToken(authHeader.split(" ")[1]);
        UserResponse userResponse = new UserResponse();

        if(user != null) {
            Team team = teamService.getTeamById(id);
            if(team != null && team.getUserId() == user.getId()) {
                teamService.deleteTeam(id);
                userResponse.setStatus(true);
                userResponse.setMessage("Team deleted");
            }else{
                userResponse.setStatus(false);
                userResponse.setMessage("Error deleting team");
            }
        }else {
            userResponse.setStatus(false);
            userResponse.setMessage("Invalid Token");
        }

        return userResponse;
    }

    @GetMapping("/leaderboard")
    public UserResponse getLeaderboard(){
        List<Team> leaderboard = teamService.getLeaderboard();
        UserResponse userResponse = new UserResponse();

        userResponse.setStatus(true);
        userResponse.setData(leaderboard);
        return userResponse;
    }
}
