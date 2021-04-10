package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_player")
public class Player {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "avg_pts")
    private Integer avgPts;

    @Column(name = "avg_assists")
    private Integer avgAssists;

    @Column(name = "avg_rebounds")
    private Integer avgRebounds;

    @Column(name = "picture")
    private String picture;

    @OneToMany(mappedBy = "player")
    @JsonIgnore
    private List<PlayerTeam> playerTeams;

    public Player() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAvgPts() {
        return this.avgPts;
    }

    public void setAvgPts(Integer avgPts) {
        this.avgPts = avgPts;
    }

    public Integer getAvgAssists() {
        return this.avgAssists;
    }

    public void setAvgAssists(Integer avgAssists) {
        this.avgAssists = avgAssists;
    }

    public Integer getAvgRebounds() {
        return this.avgRebounds;
    }

    public void setAvgRebounds(Integer avgRebounds) {
        this.avgRebounds = avgRebounds;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<PlayerTeam> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(List<PlayerTeam> playerTeams) {
        this.playerTeams = playerTeams;
    }
}
