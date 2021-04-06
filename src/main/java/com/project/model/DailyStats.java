package com.project.model;

import javax.persistence.*;

@Entity
@Table(name = "t_daily_stats")
public class DailyStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "total_pts")
    private Integer totalPts;

    @Column(name = "total_assists")
    private Integer totalAssists;

    @Column(name = "total_rebounds")
    private Integer totalRebounds;

    @Column(name = "player_id")
    private Integer playerId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalPts() {
        return this.totalPts;
    }

    public void setTotalPts(Integer totalPts) {
        this.totalPts = totalPts;
    }

    public Integer getTotalAssists() {
        return this.totalAssists;
    }

    public void setTotalAssists(Integer totalAssists) {
        this.totalAssists = totalAssists;
    }

    public Integer getTotalRebounds() {
        return this.totalRebounds;
    }

    public void setTotalRebounds(Integer totalRebounds) {
        this.totalRebounds = totalRebounds;
    }

    public Integer getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
