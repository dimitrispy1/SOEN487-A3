package com.project.service;

import com.project.interfaces.IPlayerService;
import com.project.model.Player;
import com.project.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private PlayerRepository repository;

    @Override
    public List<Player> findAll() {
        return (List<Player>) repository.findAll();
    }

    @Override
    public void updatePlayer(Player player){
        Player updatedPlayer = repository.findPlayerById(player.getId());
        if(updatedPlayer != null) {
            updatedPlayer.setAvgPts(player.getAvgPts());
            updatedPlayer.setAvgAssists(player.getAvgAssists());
            updatedPlayer.setAvgRebounds(player.getAvgRebounds());
            repository.save(updatedPlayer);
        }else{
            repository.save(player);
        }
    }

    @Override
    public void updatePlayerImage(Player player){
        Player updatedPlayer = repository.findPlayerById(player.getId());
        if(updatedPlayer != null) {
            updatedPlayer.setPicture(player.getPicture());
            repository.save(updatedPlayer);
        }
    }

}
