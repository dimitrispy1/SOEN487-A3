package com.project.controller;

import com.project.interfaces.IPlayerService;
import com.project.model.Player;
import com.project.model.User;
import com.project.model.UserResponse;
import com.project.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private TokenService tokenService;

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
}
