package com.project.interfaces;

import com.project.model.User;
import java.util.List;

public interface IUserService {

    List<User> findAll();

    void addUser(User user);

    User getUserByUsername(String username);
}
