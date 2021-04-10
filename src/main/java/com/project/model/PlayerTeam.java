package com.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "t_player_team")
public class PlayerTeam {

    @EmbeddedId
    private PlayerTeamKey id;

    @Column(name = "assigned_position")
    private String assignedPosition;

    @ManyToOne(fetch=FetchType.EAGER)
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    public PlayerTeam(){}

    public PlayerTeamKey getId() {
        return this.id;
    }

    public void setId(PlayerTeamKey id) {
        this.id = id;
    }

    public String getAssignedPosition() {
        return this.assignedPosition;
    }

    public void setAssignedPosition(String assignedPosition) {
        this.assignedPosition = assignedPosition;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
