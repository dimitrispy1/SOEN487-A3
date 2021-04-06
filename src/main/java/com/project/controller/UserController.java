package com.project.controller;

import java.util.List;
import com.project.model.User;
import com.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public String findUsers(Model model) {

        List<User> users = userService.findAll();

        return users.get(0).getUsername();
    }
}
