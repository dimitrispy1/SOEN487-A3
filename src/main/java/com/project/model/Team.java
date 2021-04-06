package com.project.model;

import javax.persistence.*;

@Entity
@Table(name = "t_team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_pts")
    private Integer totalPts;

    @Column(name = "total_assists")
    private Integer totalAssists;

    @Column(name = "total_rebounds")
    private Integer totalRebounds;

    @Column(name = "user_id")
    private Integer userId;

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

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
