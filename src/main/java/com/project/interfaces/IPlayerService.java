package com.project.interfaces;

import com.project.model.Player;

import java.util.List;

public interface IPlayerService {

    List<Player> findAll();

    void updatePlayer(Player player);

    void updatePlayerImage(Player player);
}
