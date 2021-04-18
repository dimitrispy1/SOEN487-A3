package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import com.project.model.*;
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
    private TokenService tokenService;

    @PostMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse authenticateUser(@RequestBody User user) {

        boolean authenticated = userService.authenticateUser(user);
        UserResponse userResponse = new UserResponse();

        if(authenticated) {
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

    @GetMapping("/logout")
    public UserResponse logout(@RequestHeader("Authorization") String authHeader) {
        tokenService.revokeToken(authHeader.split(" ")[1]);
        UserResponse userResponse = new UserResponse();

        userResponse.setStatus(true);
        userResponse.setMessage("User logged out");

        return userResponse;
    }
}
