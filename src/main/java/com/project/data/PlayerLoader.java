package com.project.data;

import com.project.interfaces.IPlayerService;
import com.project.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class PlayerLoader {

    @Autowired
    private PlayerDataApi api;

    @Autowired
    private IPlayerService playerService;

    public PlayerLoader() {}

    @PostConstruct
    public void run(){
        System.out.println("Loading and saving player data into DB ...");
        //updatePlayerStats();
        //addPlayerImages();
    }

    public void updatePlayerStats(){
        List<Player> players = this.api.getPlayers();
        for (Player player: players) {
            playerService.updatePlayer(player);
        }
    }

    public void addPlayerImages(){
        List<Player> players = this.api.getPlayerImages();
        for (Player player: players) {
            playerService.updatePlayerImage(player);
        }
    }
}
