package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.project.model.*;
import com.project.interfaces.IPlayerService;
import com.project.interfaces.IPlayerTeamService;
import com.project.interfaces.ITeamService;
import com.project.interfaces.IUserService;
import com.project.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private ITeamService teamService;

    @Autowired
    private IPlayerTeamService playerTeamService;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse authenticateUser(@RequestBody User user) {

        List<User> users = userService.findAll();
        UserResponse userResponse = new UserResponse();

        if(users.contains(user)) {
            userResponse.setStatus(true);
            userResponse.setMessage("User authenticated");
            User newUser = userService.getUserByUsername(user.getUsername());
            String token = tokenService.issueValidToken(newUser);
            List list = new ArrayList();
            list.add(token);
            userResponse.setData(list);
        }else{
            userResponse.setStatus(false);
            userResponse.setMessage("User not authenticated");
        }

        return userResponse;
    }

    @PostMapping(value="/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse addUser(@RequestBody User user) {
        UserResponse userResponse = new UserResponse();

        userService.addUser(user);
        userResponse.setStatus(true);
        userResponse.setMessage("User added");

        return userResponse;
    }

    @GetMapping("/players")
    public UserResponse getPlayers(@RequestHeader("Authorization") String authHeader) {
        User user = tokenService.validateToken(authHeader.split(" ")[1]);
        UserResponse userResponse = new UserResponse();

        if(user != null) {
            List<Player> players = playerService.findAll();
            userResponse.setStatus(true);
            userResponse.setData(players);
        }else{
            userResponse.setStatus(false);
            userResponse.setMessage("Invalid Token");
        }

        return userResponse;
    }

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
            teamService.deleteTeam(id);
            userResponse.setStatus(true);
            userResponse.setMessage("Team deleted");
        }else {
            userResponse.setStatus(false);
            userResponse.setMessage("Invalid Token");
        }

        return userResponse;
    }
}
