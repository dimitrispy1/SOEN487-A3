package com.project.service;

import com.project.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TokenService {

    private HashMap<String, User> payload = new HashMap<>();

    public TokenService() {}

    public String issueValidToken(User user){
        String token = RandomStringUtils.random(10, true, true);
        payload.put(token, user);
        System.out.println("User authenticated, token issued");
        return token;
    }

    public User validateToken(String token){
        if(payload.containsKey(token))
            return payload.get(token);
        else
            return null;
    }

    public void revokeToken(String token){
        if(payload.containsKey(token))
            payload.remove(token);
    }
}
