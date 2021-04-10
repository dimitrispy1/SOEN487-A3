package com.project.controller;

import java.util.List;
import com.project.model.*;
import com.project.interfaces.IPlayerService;
import com.project.interfaces.IPlayerTeamService;
import com.project.interfaces.ITeamService;
import com.project.interfaces.IUserService;
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

    @PostMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse authenticateUser(@RequestBody User user) {

        List<User> users = userService.findAll();
        UserResponse userResponse = new UserResponse();

        if(users.contains(user)) {
            userResponse.setStatus(true);
            userResponse.setMessage("User authenticated");
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
    public UserResponse getPlayers() {
        UserResponse userResponse = new UserResponse();

        List<Player> players = playerService.findAll();
        userResponse.setStatus(true);
        userResponse.setData(players);

        return userResponse;
    }

    @GetMapping("/teams")
    public UserResponse getTeamsForUser(@RequestParam Integer id) {

        List<Team> teams = teamService.getTeamsForUser(id);

        UserResponse userResponse = new UserResponse();
        userResponse.setStatus(true);
        userResponse.setData(teams);

        return userResponse;
    }

    @PostMapping(value="/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse addTeam(@RequestBody Team team) {
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
    public UserResponse deleteTeam(@RequestParam Long id) {
        UserResponse userResponse = new UserResponse();

        teamService.deleteTeam(id);
        userResponse.setStatus(true);
        userResponse.setMessage("Team deleted");

        return userResponse;
    }
}
