package com.project.service;

import com.project.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TokenService {

    private HashMap<String, User> payload = new HashMap<>();

    public TokenService() {}

    public String issueValidToken(User user){
        payload.put("0000", user);
        System.out.println("User authenticated, token issued");
        return "0000";
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
