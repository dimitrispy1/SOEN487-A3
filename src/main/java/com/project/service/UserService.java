package com.project.service;

import com.project.interfaces.IUserService;
import com.project.model.User;
import com.project.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {

        return (List<User>) repository.findAll();
    }

    @Override
    public void addUser(User user){
        repository.save(user);
    }

    @Override
    public User getUserByUsername(String username){
        return repository.getUserByUsername(username);
    }

    @Override
    public User getUserById(Integer id) {
        return repository.getUserById(id);
    }
}
