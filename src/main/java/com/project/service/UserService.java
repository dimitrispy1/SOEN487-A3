package com.project.service;

import com.project.interfaces.IUserService;
import com.project.model.User;
import com.project.repository.UserRepository;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
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
        String password = user.getPassword();
        String hashedPassword = DigestUtils.md5Hex(password).toUpperCase();
        user.setPassword(hashedPassword);
        repository.save(user);
    }

    @Override
    public User getUserByUsername(String username){
        return repository.getUserByUsername(username);
    }

    @Override
    public boolean authenticateUser(User user){
        User retrievedUser = repository.getUserByUsername(user.getUsername());
        String suppliedPassword = DigestUtils.md5Hex(user.getPassword()).toUpperCase();

        if(retrievedUser != null && suppliedPassword.equals(retrievedUser.getPassword()))
            return true;
        else
            return false;
    }

    @Override
    public User getUserById(Integer id) {
        return repository.getUserById(id);
    }
}
