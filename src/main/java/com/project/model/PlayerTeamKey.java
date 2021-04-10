package com.project.model;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayerTeamKey implements Serializable {

    @Column(name = "player_id")
    private Integer playerId;

    @Column(name = "team_id")
    private Integer teamId;

    public PlayerTeamKey(){}

    public PlayerTeamKey(Integer playerId, Integer teamId) {
        this.playerId = playerId;
        this.teamId = teamId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerTeamKey that = (PlayerTeamKey) o;
        return playerId.equals(that.playerId) &&
                teamId.equals(that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, teamId);
    }
}
