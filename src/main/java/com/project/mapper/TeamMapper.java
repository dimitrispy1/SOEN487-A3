package com.project.mapper;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class TeamMapper {

    private Integer id;
    private String name;
    private String owner;
    private Integer totalPoints;
    private Integer totalAst;
    private Integer totalReb;
    private List<PlayerMapper> guards;
    private List<PlayerMapper> forwards;

    public TeamMapper() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getTotalAst() {
        return totalAst;
    }

    public void setTotalAst(Integer totalAst) {
        this.totalAst = totalAst;
    }

    public Integer getTotalReb() {
        return totalReb;
    }

    public void setTotalReb(Integer totalReb) {
        this.totalReb = totalReb;
    }

    public List<PlayerMapper> getGuards() {
        return guards;
    }

    public void setGuards(List<PlayerMapper> guards) {
        this.guards = guards;
    }

    public List<PlayerMapper> getForwards() {
        return forwards;
    }

    public void setForwards(List<PlayerMapper> forwards) {
        this.forwards = forwards;
    }
}
